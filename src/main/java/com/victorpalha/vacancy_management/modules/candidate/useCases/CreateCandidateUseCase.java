package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.UserAlreadyExists;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    public CreateCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(CandidateEntity candidate){
        final Optional<CandidateEntity> candidateAlreadyExists = this.candidateRepository.findByEmailOrUsername(candidate.getEmail(), candidate.getUsername());
        if (candidateAlreadyExists.isPresent()) {
            throw new UserAlreadyExists();
        }
        final String passwordHashed = this.passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(passwordHashed);
        candidateRepository.save(candidate);
    }
}
