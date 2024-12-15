package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.modules.candidate.dto.FindJobByFilterRequestDTO;
import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import com.victorpalha.vacancy_management.modules.company.repository.JobRepository;
import com.victorpalha.vacancy_management.modules.company.repository.JobSpecification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FindJobByFilterUseCase {
    private final JobRepository jobRepository;
    public FindJobByFilterUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobEntity> execute(FindJobByFilterRequestDTO filter){
        return this.jobRepository.findAll(JobSpecification.filterByRequest(filter));
    }
}
