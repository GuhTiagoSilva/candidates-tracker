package com.stonks.candidatestracker.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stonks.candidatestracker.models.CertificationModel;
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
public class CertificateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String skillName;
    @JsonIgnore
    private String userName;
    private LocalDate approvedDate;

    public CertificateDto(CertificationModel certificationModel) {
        id = certificationModel.getId();
        skillName = certificationModel.getSkill().getSkillName();
        approvedDate = certificationModel.getEmitted();
    }
}
