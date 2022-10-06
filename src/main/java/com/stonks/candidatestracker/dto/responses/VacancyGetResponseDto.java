package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.UserDto;
import com.stonks.candidatestracker.models.VacancyModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
    private String country;
    private String contractType;
    private UserDto creator;

    public VacancyGetResponseDto(VacancyModel vacancyModel) {
        id = vacancyModel.getId();
        name = vacancyModel.getName();
        description = vacancyModel.getDescription();
        country = vacancyModel.getCountry().getCountry();
        contractType = vacancyModel.getContractType().getValue();
        creator = new UserDto(vacancyModel.getCreator());
    }

}
