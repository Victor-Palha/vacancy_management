package com.victorpalha.vacancy_management.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.repository.CompanyRepository;
import com.victorpalha.vacancy_management.providers.JWTProvider;

import com.victorpalha.vacancy_management.security.SecurityRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCompanyUseCase {

    final CompanyRepository companyRepository;
    final PasswordEncoder passwordEncoder;
    final JWTProvider jwtProvider;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) {
        Optional<CompanyEntity> companyExists = companyRepository.findByUsername(authCompanyDTO.getUsername());
        if (companyExists.isEmpty()) {
            throw new InvalidCredencials();
        }
        final boolean passwordsMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), companyExists.get().getPassword());
        if (!passwordsMatch) {
            throw new InvalidCredencials();
        }
        // Gen JWT
        return this.jwtProvider.generateToken(companyExists.get().getId().toString(), SecurityRoles.COMPANY);
    }
}
