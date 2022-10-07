package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestRevisionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserDto user;
    private Integer points;
    private boolean approved;
    private SkillGetResponseDto skill;



}
