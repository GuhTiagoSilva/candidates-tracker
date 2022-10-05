package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.SkillModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillModel, Long> {
}
