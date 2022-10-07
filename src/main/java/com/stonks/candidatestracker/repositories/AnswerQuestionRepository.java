package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.AnswerQuestionModel;
import com.stonks.candidatestracker.models.pk.AnswerQuestionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerQuestionRepository extends JpaRepository<AnswerQuestionModel, AnswerQuestionPK> {
}
