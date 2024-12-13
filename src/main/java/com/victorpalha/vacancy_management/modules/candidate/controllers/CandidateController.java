package com.victorpalha.vacancy_management.modules.candidate.controllers;

import com.victorpalha.vacancy_management.exceptions.UserAlreadyExists;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;

    public CandidateController(CreateCandidateUseCase createCandidateUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){
        try{
            this.createCandidateUseCase.execute(candidate);
            final HashMap<String, String> response = new HashMap<>();
            response.put("message", "Candidate created");

            return ResponseEntity.status(201).body(response);
        }catch (UserAlreadyExists e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
