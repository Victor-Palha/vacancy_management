package com.victorpalha.vacancy_management.modules.company.useCases;

import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import com.victorpalha.vacancy_management.modules.company.repository.JobRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    private final JobRepository jobRepository;
    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity execute(JobEntity job) {
        return this.jobRepository.save(job);
    }
}
