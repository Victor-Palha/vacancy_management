package com.victorpalha.vacancy_management.modules.company.useCases;

import com.victorpalha.vacancy_management.exceptions.CompanyAlreadyExists;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity execute(CompanyEntity company){
        final Optional<CompanyEntity> companyAlreadyRegister = this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail());
        final Optional<CompanyEntity> companyAlreadyExists = this.companyRepository.findByNameOrIdentifier(company.getName(), company.getIdentifier());
        if (companyAlreadyExists.isPresent() || companyAlreadyRegister.isPresent()) {
            throw new CompanyAlreadyExists();
        }
        return this.companyRepository.save(company);
    }
}
