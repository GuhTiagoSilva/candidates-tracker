package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerModel, Long> {
}
