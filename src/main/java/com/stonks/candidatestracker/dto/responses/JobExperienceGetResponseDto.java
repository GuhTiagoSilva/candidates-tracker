package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.models.JobExperienceModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobExperienceGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String companyName;
    private String jobTitle;
    private String description;

    public JobExperienceGetResponseDto(JobExperienceModel jobExperience) {
        id = jobExperience.getId();
        companyName = jobExperience.getCompanyName();
        jobTitle = jobExperience.getJobTitle();
        description = jobExperience.getDescription();
    }
}
