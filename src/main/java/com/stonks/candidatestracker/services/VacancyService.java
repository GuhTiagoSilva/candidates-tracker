package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.EmailDetailsDto;
import com.stonks.candidatestracker.dto.VacancyInsertDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserVacancyRepository userVacancyRepository;
    private final AuthService authService;

    private final EmailService emailService;

    public VacancyService(VacancyRepository vacancyRepository, AuthService authService, UserVacancyRepository userVacancyRepository, EmailService emailService) {
        this.vacancyRepository = vacancyRepository;
        this.authService = authService;
        this.userVacancyRepository = userVacancyRepository;
        this.emailService = emailService;
    }

    @Transactional(readOnly = true)
    public List<VacancyGetResponseDto> findAllOpenVacancies() {
        List<VacancyModel> vacancies = vacancyRepository.findAll().stream().filter(x -> x.isConcluded() == false).collect(Collectors.toList());
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
    public void applyToVacancy(Long vacancyId) {

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

        userVacancyRepository.save(userVacancyModel);
        emailService.sendMail(emailDetailsDto);

    }

}
