package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestModel, Long> {
}
