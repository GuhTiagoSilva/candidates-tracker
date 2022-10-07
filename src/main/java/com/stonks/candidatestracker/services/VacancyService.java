package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.EmailDetailsDto;
import com.stonks.candidatestracker.dto.VacancyInsertDto;
import com.stonks.candidatestracker.dto.VacancyUpdateDto;
import com.stonks.candidatestracker.dto.responses.VacancyGetResponseDto;
import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.models.UserVacancyModel;
import com.stonks.candidatestracker.models.VacancyModel;
import com.stonks.candidatestracker.models.pk.UserVacancyPK;
import com.stonks.candidatestracker.repositories.UserVacancyRepository;
import com.stonks.candidatestracker.repositories.VacancyRepository;
import com.stonks.candidatestracker.services.exceptions.BusinessException;
import com.stonks.candidatestracker.services.exceptions.ResourceNotFoundException;
import com.stonks.candidatestracker.utils.MailMessageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserVacancyRepository userVacancyRepository;
    private final AuthService authService;

    private final EmailService emailService;

    private final S3ServicePdfUploadImpl s3ServicePdfUpload;

    private String awsBucketEndpoint = "https://stonks-challenge-bucket.s3.sa-east-1.amazonaws.com";

    public VacancyService(VacancyRepository vacancyRepository,
                          AuthService authService,
                          UserVacancyRepository userVacancyRepository,
                          EmailService emailService,
                          S3ServicePdfUploadImpl s3ServicePdfUpload
    ) {
        this.vacancyRepository = vacancyRepository;
        this.authService = authService;
        this.userVacancyRepository = userVacancyRepository;
        this.emailService = emailService;
        this.s3ServicePdfUpload = s3ServicePdfUpload;
    }

    @Transactional(readOnly = true)
    public List<VacancyGetResponseDto> findAllOpenVacancies() {
        List<VacancyModel> vacancies = vacancyRepository.findAllByConcludedIsFalse();
        return vacancies.stream().map(vacancy -> new VacancyGetResponseDto(vacancy)).collect(Collectors.toList());
    }

    @Transactional
    public void createVacancy(VacancyInsertDto vacancyInsertDto) {
        UserModel creator = authService.authenticated();
        VacancyModel vacancyModel = new VacancyModel();
        vacancyModel.setCreator(creator);
        vacancyModel.setConcluded(Boolean.FALSE);
        vacancyModel.setDescription(vacancyInsertDto.getDescription());
        vacancyModel.setCountry(vacancyInsertDto.getCountry());
        vacancyModel.setContractType(vacancyInsertDto.getContractType());
        vacancyModel.setName(vacancyInsertDto.getName());
        vacancyRepository.save(vacancyModel);
    }

    @Transactional
    public void updateVacancy(Long vacancyId, VacancyUpdateDto vacancyUpdateDto) {

        UserModel authUser = authService.authenticated();
        VacancyModel vacancyModel = vacancyRepository.findById(vacancyId).orElseThrow(() -> new ResourceNotFoundException("Vacancy Not Found"));

        if (!vacancyModel.getCreator().getId().equals(authUser.getId()))
            throw new BusinessException("You are trying to update a vacancy that belongs to another creator.");

        vacancyModel.setConcluded(vacancyUpdateDto.isConcluded());
        vacancyModel.setDescription(vacancyUpdateDto.getDescription());
        vacancyModel.setCountry(vacancyUpdateDto.getCountry());
        vacancyModel.setContractType(vacancyUpdateDto.getContractType());
        vacancyModel.setName(vacancyUpdateDto.getName());
        vacancyRepository.save(vacancyModel);
    }

    @Transactional
    public void applyToVacancy(Long vacancyId, MultipartFile multipartFile) {

        UserModel authUser = authService.authenticated();
        VacancyModel vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new ResourceNotFoundException("Vacancy not found: " + vacancyId));
        UserModel vacancyCreator = vacancy.getCreator();

        UserVacancyPK userVacancyPK = new UserVacancyPK();
        userVacancyPK.setVacancyModel(vacancy);
        userVacancyPK.setUserModel(authUser);

        if (userVacancyRepository.existsById(userVacancyPK))
            throw new BusinessException("This user have already applied to this vacancy.");

        String htmlBodyEmail = MailMessageUtils.getDefaultHtmlMailBody();

        String subject = "Alguém se interessou pela vaga: " + vacancy.getName() + ". Venha dar uma olhada!";

        htmlBodyEmail = htmlBodyEmail.replaceAll("_firstName_", vacancyCreator.getFirstName() + " " + vacancyCreator.getLastName());
        htmlBodyEmail = htmlBodyEmail.replaceAll("_appliedUserEmail_", authUser.getEmail());
        htmlBodyEmail = htmlBodyEmail.replaceAll("_vacancyName_", vacancy.getName());
        htmlBodyEmail = htmlBodyEmail.replaceAll("userId", String.valueOf(authUser.getId()));


        EmailDetailsDto emailDetailsDto = EmailDetailsDto.builder()
                .msgBody(htmlBodyEmail)
                .subject(subject)
                .attachment(null)
                .recipient(vacancyCreator.getEmail())
                .build();

        UserVacancyModel userVacancyModel = new UserVacancyModel();
        userVacancyModel.setUserVacancyPK(userVacancyPK);

        if (Objects.nonNull(multipartFile) && !multipartFile.isEmpty()) {
            String url = this.awsBucketEndpoint + s3ServicePdfUpload.uploadFile(multipartFile).getPath();
            userVacancyModel.setResume(url);
        }
        userVacancyRepository.save(userVacancyModel);
        try {
            emailService.sendMail(emailDetailsDto);
        } catch (Exception e) {
            log.error("ERROR: {}", e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public List<VacancyGetResponseDto> findAllByCreator() {
        UserModel creator = authService.authenticated();
        return vacancyRepository.findAllByCreator(creator).stream().map(vacancy -> new VacancyGetResponseDto(vacancy))
                .collect(Collectors.toList());
    }

}
