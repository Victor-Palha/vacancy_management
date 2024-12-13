package com.victorpalha.vacancy_management.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String JWT_SECRET;
    final CompanyRepository companyRepository;
    final PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
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
        Algorithm algorithm = Algorithm.HMAC256(this.JWT_SECRET);

        String jwtToken = JWT.create()
                .withIssuer("AshFoundation")
                .withSubject(companyExists.get().getId().toString())
                .sign(algorithm);

        return jwtToken;
    }
}
