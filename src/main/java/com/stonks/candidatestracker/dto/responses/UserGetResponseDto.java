package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.RoleDto;
import com.stonks.candidatestracker.dto.TestDto;
import com.stonks.candidatestracker.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private boolean isOpenToWork;
    private RoleDto role;
    private List<JobExperienceGetResponseDto> jobExperiences = new ArrayList<>();
    private List<SkillGetResponseDto> skills = new ArrayList<>();
    private List<AcademicFormationGetResponseDto> academicFormation = new ArrayList<>();

    private List<TestDto> tests = new ArrayList<>();

    public UserGetResponseDto(UserModel user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        cpf = user.getCpf();
        isOpenToWork = user.isOpenToWork();
        role = new RoleDto(user.getRoleModel());
        user.getExperiences().forEach(experience -> this.jobExperiences.add(new JobExperienceGetResponseDto(experience)));
        user.getSkills().forEach(skill -> this.skills.add(new SkillGetResponseDto(skill)));
        user.getFormations().forEach(formation -> this.academicFormation.add(new AcademicFormationGetResponseDto(formation)));
        user.getTests().forEach(test -> this.tests.add(new TestDto(test)));
    }

}
