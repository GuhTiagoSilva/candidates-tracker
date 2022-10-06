package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.dto.responses.AcademicFormationGetResponseDto;
import com.stonks.candidatestracker.dto.responses.JobExperienceGetResponseDto;
import com.stonks.candidatestracker.dto.responses.SkillGetResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto extends UserDto {
    private List<JobExperienceGetResponseDto> jobExperiences = new ArrayList<>();
    private List<SkillGetResponseDto> skills = new ArrayList<>();
    private List<AcademicFormationGetResponseDto> academicFormation = new ArrayList<>();
    private List<TestDto> tests = new ArrayList<>();
}
