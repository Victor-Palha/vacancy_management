package com.victorpalha.vacancy_management.modules.candidate.controllers;

import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.candidate.dto.AuthCandidateDTO;
import com.victorpalha.vacancy_management.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    final private AuthCandidateUseCase authCandidateUseCase;

    public AuthCandidateController(AuthCandidateUseCase authCandidateUseCase) {
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @PostMapping("/candidate")
    public ResponseEntity<Object> authCandidate(@RequestBody AuthCandidateDTO authCandidateDTO) {
        try {
            final String token = this.authCandidateUseCase.execute(authCandidateDTO);
            final HashMap<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok().body(response);
        } catch (InvalidCredencials error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
