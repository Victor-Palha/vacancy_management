package com.victorpalha.vacancy_management.modules.candidate.repository;

import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByEmailOrUsername(String email, String username);
    Optional<CandidateEntity> findByEmail(String email);
}
