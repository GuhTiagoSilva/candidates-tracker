package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.JobExperienceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExperienceRepository extends JpaRepository<JobExperienceModel, Long> {
}
