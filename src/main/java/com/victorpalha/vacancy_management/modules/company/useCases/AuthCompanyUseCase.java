package com.victorpalha.vacancy_management.modules.company.useCases;

import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.repository.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthCompanyUseCase {

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

        return "JWT";
    }
}
