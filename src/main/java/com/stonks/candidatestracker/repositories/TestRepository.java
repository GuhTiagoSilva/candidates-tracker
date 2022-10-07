package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.SkillModel;
import com.stonks.candidatestracker.models.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestModel, Long> {

    List<TestModel> findAllBySkill(SkillModel skillModel);

}
