package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.QuestionDto;
import com.stonks.candidatestracker.dto.TestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGetResponseDto extends TestDto {

    private List<QuestionDto> questions = new ArrayList<>();

}
