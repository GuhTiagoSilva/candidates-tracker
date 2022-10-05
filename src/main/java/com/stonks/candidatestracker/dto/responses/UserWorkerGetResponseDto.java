package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.RoleDto;
import com.stonks.candidatestracker.dto.TestDto;
import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWorkerGetResponseDto extends UserDto {
    private boolean isOpenToWork;
    private RoleDto role;
    private List<JobExperienceGetResponseDto> jobExperiences = new ArrayList<>();
    private List<SkillGetResponseDto> skills = new ArrayList<>();
    private List<AcademicFormationGetResponseDto> academicFormation = new ArrayList<>();
    private List<TestDto> tests = new ArrayList<>();

    public UserWorkerGetResponseDto(UserModel user) {
        super.setId(user.getId());
        super.setFirstName(user.getFirstName());
        super.setLastName(user.getLastName());
        super.setCpf(user.getCpf());
        isOpenToWork = user.isOpenToWork();
        role = new RoleDto(user.getRoleModel());
        user.getExperiences().forEach(experience -> this.jobExperiences.add(new JobExperienceGetResponseDto(experience)));
        user.getSkills().forEach(skill -> this.skills.add(new SkillGetResponseDto(skill)));
        user.getFormations().forEach(formation -> this.academicFormation.add(new AcademicFormationGetResponseDto(formation)));
        user.getTests().forEach(test -> this.tests.add(new TestDto(test)));
    }

}
