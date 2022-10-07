package com.stonks.candidatestracker.dto;

import lombok.Data;

@Data
public class VacancyUpdateDto extends VacancyInsertDto {
    private boolean concluded;
}
