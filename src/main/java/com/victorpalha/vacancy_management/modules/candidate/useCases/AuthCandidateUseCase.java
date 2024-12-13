package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.candidate.dto.AuthCandidateDTO;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import com.victorpalha.vacancy_management.providers.JWTProvider;
import com.victorpalha.vacancy_management.security.SecurityRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCandidateUseCase {

    final private CandidateRepository candidateRepository;
    final private PasswordEncoder passwordEncoder;
    final JWTProvider jwtProvider;

    public AuthCandidateUseCase(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String execute(AuthCandidateDTO authCandidateDTO) {
        Optional<CandidateEntity> userExists = this.candidateRepository.findByEmail(authCandidateDTO.email());
        if(userExists.isEmpty()){
            throw new InvalidCredencials();
        }
        final boolean passwordsMatch = this.passwordEncoder.matches(authCandidateDTO.password(), userExists.get().getPassword());
        if(!passwordsMatch){
            throw new InvalidCredencials();
        }

        return this.jwtProvider.generateToken(userExists.get().getId().toString(), SecurityRoles.CANDIDATE);
    }
}
