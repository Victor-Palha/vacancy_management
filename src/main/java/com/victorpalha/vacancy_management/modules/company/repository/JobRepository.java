package com.victorpalha.vacancy_management.modules.company.repository;

import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {}
