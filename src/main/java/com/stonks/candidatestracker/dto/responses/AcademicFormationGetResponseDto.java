package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.enums.AcademicFormationType;
import com.stonks.candidatestracker.models.AcademicFormationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicFormationGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String institutionName;
    // get value from enum AcademicFormationType
    private String academicFormationType;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public AcademicFormationGetResponseDto(AcademicFormationModel academicFormationModel) {
        id = academicFormationModel.getId();
        institutionName = academicFormationModel.getInstitutionName();
        academicFormationType = academicFormationModel.getAcademicFormationType().getValue();
        description = academicFormationModel.getDescription();
        startDate = academicFormationModel.getStartDate();
        endDate = academicFormationModel.getEndDate();
    }
}
