package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
}
