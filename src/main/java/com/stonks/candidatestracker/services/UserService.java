package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.enums.AcademicFormationType;
import com.stonks.candidatestracker.models.*;
import com.stonks.candidatestracker.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SkillRepository skillRepository;
    private final AcademicFormationRepository academicFormationRepository;
    private final JobExperienceRepository jobExperienceRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       JobExperienceRepository jobExperienceRepository,
                       AcademicFormationRepository academicFormationRepository,
                       SkillRepository skillRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jobExperienceRepository = jobExperienceRepository;
        this.skillRepository = skillRepository;
        this.academicFormationRepository = academicFormationRepository;
    }


    @Transactional
    public void createUser(UserInsertDto userDto) {

        RoleModel role = roleRepository.getReferenceById(userDto.getRole().getId());
        UserModel user = new UserModel();

        if (role.getAuthority().equals("ROLE_WORKER")) {
            List<JobExperienceModel> jobExperiences = new ArrayList<>();

            userDto.getJobExperiences().forEach(jobExperience -> {
                final JobExperienceModel jobExperienceModel = new JobExperienceModel();
                jobExperienceModel.setJobTitle(jobExperience.getJobTitle());
                jobExperienceModel.setDescription(jobExperience.getDescription());
                jobExperienceModel.setCompanyName(jobExperience.getCompanyName());
                jobExperienceModel.setEndDate(jobExperience.getEndDate());
                jobExperienceModel.setStartDate(jobExperience.getStartDate());
                jobExperienceModel.setWorker(new UserModel(1L)); // authenticated user
                jobExperiences.add(jobExperienceModel);
            });

            jobExperienceRepository.saveAll(jobExperiences);

            Set<SkillModel> skills = new HashSet<>();

            userDto.getSkills().forEach(skillModel -> {
                final SkillModel skill = new SkillModel();
                skill.setSkillName(skillModel.getSkillName());
                skills.add(skill);
            });

            skillRepository.saveAll(skills);

            List<AcademicFormationModel> academicFormations = new ArrayList<>();

            userDto.getAcademicFormation().forEach(academicFormation -> {
                final AcademicFormationModel academicFormationModel = new AcademicFormationModel();
                academicFormationModel.setAcademicFormationType(AcademicFormationType.fromValue(academicFormation.getAcademicFormationType()));
                academicFormationModel.setUser(new UserModel(1L)); // get auth user
                academicFormationModel.setEndDate(academicFormation.getEndDate());
                academicFormationModel.setStartDate(academicFormation.getStartDate());
                academicFormationModel.setDescription(academicFormation.getDescription());
                academicFormationModel.setInstitutionName(academicFormation.getInstitutionName());
                academicFormations.add(academicFormationModel);
            });

            academicFormationRepository.saveAll(academicFormations);

            user.setExperiences(jobExperiences);
            user.setFormations(academicFormations);
            user.setSkills(skills);
        }

        user.setCpf(userDto.getCpf());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setIsOpenToWork(userDto.isOpenToWork());
        user.setRoleModel(role);

        userRepository.save(user);
    }

}
