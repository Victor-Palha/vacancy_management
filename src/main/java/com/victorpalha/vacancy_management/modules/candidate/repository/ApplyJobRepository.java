package com.victorpalha.vacancy_management.modules.candidate.repository;

import com.victorpalha.vacancy_management.modules.candidate.entities.ApplyJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobsEntity, UUID> { }
