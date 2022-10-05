package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
}
