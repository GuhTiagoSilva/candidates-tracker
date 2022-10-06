package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.enums.ContractType;
import com.stonks.candidatestracker.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyInsertDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Country country;
    private ContractType contractType;
}
