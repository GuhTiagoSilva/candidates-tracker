package com.stonks.candidatestracker.repositories;

import com.stonks.candidatestracker.models.CertificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificationModel, Long> {
}
