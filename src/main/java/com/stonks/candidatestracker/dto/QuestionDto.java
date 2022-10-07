package com.stonks.candidatestracker.dto;

import com.stonks.candidatestracker.models.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private Long markedAnswerId;

    public QuestionDto(QuestionModel question) {
        id = question.getId();
    }

}
