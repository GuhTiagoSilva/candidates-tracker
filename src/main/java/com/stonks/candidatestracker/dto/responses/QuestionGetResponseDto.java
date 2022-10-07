package com.stonks.candidatestracker.dto.responses;

import com.stonks.candidatestracker.dto.AnswerDto;
import com.stonks.candidatestracker.models.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGetResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String question;
    private List<AnswerDto> answers = new ArrayList<>();

    public QuestionGetResponseDto(QuestionModel questionModel) {
        this.id = questionModel.getId();
        this.question = questionModel.getQuestion();
        questionModel.getAnswer().forEach(answer -> {
            this.answers.add(new AnswerDto(answer));
        });
    }

}
