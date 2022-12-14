package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.dto.UserUpdateDto;
import com.stonks.candidatestracker.dto.responses.AcademicFormationGetResponseDto;
import com.stonks.candidatestracker.dto.responses.JobExperienceGetResponseDto;
import com.stonks.candidatestracker.dto.responses.SkillGetResponseDto;
import com.stonks.candidatestracker.dto.responses.UserWorkerGetResponseDto;
import com.stonks.candidatestracker.models.*;
import com.stonks.candidatestracker.repositories.*;
import com.stonks.candidatestracker.services.exceptions.BusinessException;
import com.stonks.candidatestracker.services.exceptions.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Log4j2
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SkillRepository skillRepository;
    private final AcademicFormationRepository academicFormationRepository;
    private final JobExperienceRepository jobExperienceRepository;
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    private final S3ServiceImageUpload s3ServiceImageUpload;

    private String awsBucketEndpoint = "https://stonks-challenge-bucket.s3.sa-east-1.amazonaws.com";

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       JobExperienceRepository jobExperienceRepository,
                       AcademicFormationRepository academicFormationRepository,
                       SkillRepository skillRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       S3ServiceImageUpload s3ServiceImageUpload,
                       AuthService authService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jobExperienceRepository = jobExperienceRepository;
        this.skillRepository = skillRepository;
        this.academicFormationRepository = academicFormationRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.s3ServiceImageUpload = s3ServiceImageUpload;
    }


    @Transactional
    public void createUser(UserInsertDto userDto) {
        RoleModel roleModel = roleRepository.getReferenceById(userDto.getRole().getId());
        UserModel user = new UserModel();
        copyDtoToEntity(userDto, user);
        user.setRoleModel(roleModel);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public UserWorkerGetResponseDto findAuthUserInformation() {
        UserModel userModel = authService.authenticated();
        return new UserWorkerGetResponseDto(userModel);
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateDto userDto, MultipartFile multipartFile) {
        UserModel authUser = authService.authenticated();
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + userId));

        if (!user.getId().equals(authUser.getId())) {
            throw new BusinessException("No permission!");
        }

        if (user.getRoleModel().getAuthority().equals("ROLE_WORKER")) {
            List<JobExperienceModel> jobExperiences = new ArrayList<>();

            for (JobExperienceGetResponseDto jobExperience : userDto.getJobExperiences()) {

                JobExperienceModel jobExperienceModel = new JobExperienceModel();

                if (Objects.nonNull(jobExperience.getId()) && jobExperience.getId() != 0)
                    jobExperienceModel = jobExperienceRepository.getReferenceById(jobExperience.getId());

                jobExperienceModel.setJobTitle(jobExperience.getJobTitle());
                jobExperienceModel.setDescription(jobExperience.getDescription());
                jobExperienceModel.setCompanyName(jobExperience.getCompanyName());
                jobExperienceModel.setEndDate(jobExperience.getEndDate());
                jobExperienceModel.setStartDate(jobExperience.getStartDate());
                jobExperienceModel.setWorker(user);
                jobExperienceModel = jobExperienceRepository.save(jobExperienceModel);
                jobExperiences.add(jobExperienceModel);
            }

            Set<SkillModel> skills = new HashSet<>();

            for (SkillGetResponseDto skillModel : userDto.getSkills()) {
                SkillModel skill = new SkillModel();

                if (Objects.nonNull(skillModel.getId()) && skillModel.getId() != 0)
                    skill = skillRepository.getReferenceById(skillModel.getId());

                skill.setSkillName(skillModel.getSkillName());
                skill = skillRepository.save(skill);
                skills.add(skill);
            }

            List<AcademicFormationModel> academicFormations = new ArrayList<>();

            for (AcademicFormationGetResponseDto academicFormation : userDto.getAcademicFormation()) {
                AcademicFormationModel academicFormationModel = new AcademicFormationModel();

                if (Objects.nonNull(academicFormation.getId()) && academicFormation.getId() != 0)
                    academicFormationModel = academicFormationRepository.getReferenceById(academicFormation.getId());

                academicFormationModel.setAcademicFormationType(academicFormation.getAcademicFormationType());
                academicFormationModel.setEndDate(academicFormation.getEndDate());
                academicFormationModel.setStartDate(academicFormation.getStartDate());
                academicFormationModel.setDescription(academicFormation.getDescription());
                academicFormationModel.setInstitutionName(academicFormation.getInstitutionName());
                academicFormationModel.setUser(user);
                academicFormationModel = academicFormationRepository.save(academicFormationModel);
                academicFormations.add(academicFormationModel);
            }
            user.setExperiences(jobExperiences);
            user.setFormations(academicFormations);
            user.setSkills(skills);
        }

        this.copyDtoToEntity(userDto, user);
        if (Objects.nonNull(multipartFile) && !multipartFile.isEmpty()) {
            String url = this.awsBucketEndpoint + s3ServiceImageUpload.uploadFile(multipartFile).getPath();
            user.setPhoto(url);
        }
        userRepository.save(user);
    }

    private void copyDtoToEntity(UserDto userDto, UserModel user) {
        if (!userDto.getCpf().equals(user.getCpf())) {
            user.setCpf(userDto.getCpf());
        }

        if (!userDto.getEmail().equals(user.getEmail())) {
            user.setEmail(userDto.getEmail());
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setIsOpenToWork(userDto.isOpenToWork());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByEmail(username);

        if (Objects.nonNull(userModel))
            return userModel;

        log.error("User not found for e-mail: {}", username);
        throw new UsernameNotFoundException("User Not Found For Email: " + username);
    }
}
