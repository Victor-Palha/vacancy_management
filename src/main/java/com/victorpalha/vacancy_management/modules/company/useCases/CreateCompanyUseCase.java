package com.victorpalha.vacancy_management.modules.company.useCases;

import com.victorpalha.vacancy_management.exceptions.CompanyAlreadyExists;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.repository.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    public CreateCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(CompanyEntity company){
        final Optional<CompanyEntity> companyAlreadyRegister = this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail());
        final Optional<CompanyEntity> companyAlreadyExists = this.companyRepository.findByNameOrIdentifier(company.getName(), company.getIdentifier());
        if (companyAlreadyExists.isPresent() || companyAlreadyRegister.isPresent()) {
            throw new CompanyAlreadyExists();
        }
        final String passwordHashed = this.passwordEncoder.encode(company.getPassword());
        company.setPassword(passwordHashed);
        this.companyRepository.save(company);
    }
}
