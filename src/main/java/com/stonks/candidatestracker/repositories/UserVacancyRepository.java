package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.UserVacancyModel;
import com.stonks.candidatestracker.models.pk.UserVacancyPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVacancyRepository extends JpaRepository<UserVacancyModel, UserVacancyPK> {
}
