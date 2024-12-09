package com.victorpalha.vacancy_management.modules.candidate.controllers;

import com.victorpalha.vacancy_management.exceptions.UserAlreadyExists;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    final
    CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/")
    public void create(@Valid @RequestBody CandidateEntity candidate){
        final Optional<CandidateEntity> candidateAlreadyExists = this.candidateRepository.findByEmailOrUsername(candidate.getEmail(), candidate.getUsername());
        if (candidateAlreadyExists.isPresent()) {
            throw new UserAlreadyExists();
        }
        this.candidateRepository.save(candidate);
    }
}
