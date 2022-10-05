package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecruiterGetResponseDto extends UserDto {
    private List<VacancyGetResponseDto> vacanciesOpenedByThisUser = new ArrayList<>();
}
