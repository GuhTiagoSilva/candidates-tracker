package com.stonks.candidatestracker.dto.responses;

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
    private String userName;
    private LocalDate approvedDate;
}
