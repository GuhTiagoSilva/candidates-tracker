package com.stonks.candidatestracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestInsertDto extends TestDto {

    private List<QuestionDto> questions = new ArrayList<>();

}
