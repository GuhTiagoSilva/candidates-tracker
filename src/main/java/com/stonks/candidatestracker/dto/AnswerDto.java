package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.models.AnswerModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String answerDescription;

    public AnswerDto(AnswerModel answerModel) {
        id = answerModel.getId();
        answerDescription = answerModel.getAnswerDescription();
    }

}
