package com.stonks.candidatestracker.services;

import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.dto.UserInsertDto;
import com.stonks.candidatestracker.dto.UserUpdateDto;
import com.stonks.candidatestracker.models.*;
import com.stonks.candidatestracker.repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       JobExperienceRepository jobExperienceRepository,
                       AcademicFormationRepository academicFormationRepository,
                       SkillRepository skillRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       AuthService authService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jobExperienceRepository = jobExperienceRepository;
        this.skillRepository = skillRepository;
        this.academicFormationRepository = academicFormationRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
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
    public UserDto findAuthUserInformation() {
        UserModel userModel = authService.authenticated();
        return new UserDto(userModel);
    }

    @Transactional
    public void updateUser(UserUpdateDto userDto) {

        UserModel user = authService.authenticated();

        if (user.getRoleModel().getAuthority().equals("ROLE_WORKER")) {
            List<JobExperienceModel> jobExperiences = new ArrayList<>();

            userDto.getJobExperiences().forEach(jobExperience -> {
                final JobExperienceModel jobExperienceModel = new JobExperienceModel();
                jobExperienceModel.setJobTitle(jobExperience.getJobTitle());
                jobExperienceModel.setDescription(jobExperience.getDescription());
                jobExperienceModel.setCompanyName(jobExperience.getCompanyName());
                jobExperienceModel.setEndDate(jobExperience.getEndDate());
                jobExperienceModel.setStartDate(jobExperience.getStartDate());
                jobExperienceModel.setWorker(user);
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
                academicFormationModel.setAcademicFormationType(academicFormation.getAcademicFormationType());
                academicFormationModel.setEndDate(academicFormation.getEndDate());
                academicFormationModel.setStartDate(academicFormation.getStartDate());
                academicFormationModel.setDescription(academicFormation.getDescription());
                academicFormationModel.setInstitutionName(academicFormation.getInstitutionName());
                academicFormationModel.setUser(user);
                academicFormations.add(academicFormationModel);
            });

            academicFormationRepository.saveAll(academicFormations);

            user.setExperiences(jobExperiences);
            user.setFormations(academicFormations);
            user.setSkills(skills);
        }

        this.copyDtoToEntity(userDto, user);
        userRepository.save(user);
    }

    private void copyDtoToEntity(UserDto userDto, UserModel user) {
        user.setCpf(userDto.getCpf());
        user.setEmail(userDto.getEmail());
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
