package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.CandidateNotFound;
import com.victorpalha.vacancy_management.exceptions.JobNotFound;
import com.victorpalha.vacancy_management.modules.candidate.entities.ApplyJobsEntity;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.ApplyJobRepository;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import com.victorpalha.vacancy_management.modules.company.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ApplyForJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyForJobCandidateUseCase applyForJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply for job if candidate doesn't exists")
    public void should_not_be_able_to_apply_for_job_if_candidate_doesnt_exists() {
        UUID userId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();
        try{
            this.applyForJobCandidateUseCase.execute(userId, jobId);
        }catch (Exception e){
            assertThat(e).isInstanceOf(CandidateNotFound.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply for job if job doesn't exists")
    public void should_not_be_able_to_apply_for_job_if_job_doesnt_exists() {
        UUID userId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();
        CandidateEntity candidateMock = new CandidateEntity();
        candidateMock.setId(userId);
        when(candidateRepository.findById(userId)).thenReturn(Optional.of(candidateMock));

        try{
            this.applyForJobCandidateUseCase.execute(userId, jobId);
        }catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFound.class);
        }
    }

    @Test
    @DisplayName("Should be able to apply for job if candidate and job exists")
    public void should_be_able_to_apply_for_job_if_candidate_and_job_exists() {
        UUID userId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();
        ApplyJobsEntity applyJob = ApplyJobsEntity
                .builder()
                .candidateId(userId)
                .jobId(jobId)
//                .id(UUID.randomUUID())
//                .createdAt(LocalDateTime.now())
                .build();

        when(candidateRepository.findById(userId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJob);

        var jobCreated = this.applyForJobCandidateUseCase.execute(userId, jobId);
        assertThat(jobCreated).hasFieldOrProperty("id");
    }
}
