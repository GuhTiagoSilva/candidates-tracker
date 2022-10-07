package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.UserModel;
import com.stonks.candidatestracker.models.VacancyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<VacancyModel, Long> {

    List<VacancyModel> findAllByCreator(UserModel creator);
    List<VacancyModel> findAllByConcludedIsFalse();

}
