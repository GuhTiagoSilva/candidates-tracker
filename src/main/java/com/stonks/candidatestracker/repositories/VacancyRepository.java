package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<VacancyModel, Long> {
}
