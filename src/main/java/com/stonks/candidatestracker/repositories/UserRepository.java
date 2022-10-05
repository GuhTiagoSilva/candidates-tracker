package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
