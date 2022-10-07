package com.stonks.candidatestracker.models.pk;

import com.stonks.candidatestracker.models.AnswerModel;
import com.stonks.candidatestracker.models.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerQuestionPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private AnswerModel answerModel;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionModel questionModel;

}
