package com.stonks.candidatestracker.models;

import com.stonks.candidatestracker.models.pk.AnswerQuestionPK;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_answer_question")
public class AnswerQuestionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AnswerQuestionPK answerQuestionPK = new AnswerQuestionPK();

}
