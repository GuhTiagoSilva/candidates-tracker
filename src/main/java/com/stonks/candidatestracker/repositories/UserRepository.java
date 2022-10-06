package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    List<UserModel> findAllByIsOpenToWorkTrue();

    UserModel findByEmail(String email);

}
