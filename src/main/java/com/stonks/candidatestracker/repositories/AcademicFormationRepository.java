package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.AcademicFormationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicFormationRepository extends JpaRepository<AcademicFormationModel, Long> {
}
