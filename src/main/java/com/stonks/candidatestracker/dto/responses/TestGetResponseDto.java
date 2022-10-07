package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.models.TestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long testId;

    private SkillGetResponseDto skill = new SkillGetResponseDto();

    private List<QuestionGetResponseDto> questions = new ArrayList<>();

    public TestGetResponseDto(TestModel testModel) {

        testId = testModel.getId();

        testModel.getQuestions().forEach(question -> {
            this.questions.add(new QuestionGetResponseDto(question));
        });
        skill = new SkillGetResponseDto(testModel.getSkill());
    }

}
