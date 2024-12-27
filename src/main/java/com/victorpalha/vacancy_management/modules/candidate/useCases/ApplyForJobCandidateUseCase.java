package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.CandidateNotFound;
import com.victorpalha.vacancy_management.exceptions.JobNotFound;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.ApplyJobRepository;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import com.victorpalha.vacancy_management.modules.company.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ApplyForJobCandidateUseCase {

    final private CandidateRepository candidateRepository;
    final private JobRepository jobRepository;
    final private ApplyJobRepository applyJobRepository;
    public ApplyForJobCandidateUseCase(CandidateRepository candidateRepository, JobRepository jobRepository, ApplyJobRepository applyJobRepository) {
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.applyJobRepository = applyJobRepository;
    }
    public void execute(UUID idCandidate, UUID idJob) {
        Optional<CandidateEntity> candidateExists = this.candidateRepository.findById(idCandidate);
        if (candidateExists.isEmpty()){
            throw new CandidateNotFound();
        }
        Optional<JobEntity> jobExists = this.jobRepository.findById(idJob);
        if (jobExists.isEmpty()){
            throw new JobNotFound();
        }


    }
}
