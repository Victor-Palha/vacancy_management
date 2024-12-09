package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.UserAlreadyExists;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(CandidateEntity candidate){
        final Optional<CandidateEntity> candidateAlreadyExists = this.candidateRepository.findByEmailOrUsername(candidate.getEmail(), candidate.getUsername());
        if (candidateAlreadyExists.isPresent()) {
            throw new UserAlreadyExists();
        }
        return candidateRepository.save(candidate);
    }
}
