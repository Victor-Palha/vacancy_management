package com.victorpalha.vacancy_management.modules.company.controllers;

import com.victorpalha.vacancy_management.exceptions.CompanyAlreadyExists;
import com.victorpalha.vacancy_management.modules.company.entities.CompanyEntity;
import com.victorpalha.vacancy_management.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CreateCompanyUseCase createCompanyUseCase;
    public CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company){
        try{
            this.createCompanyUseCase.execute(company);
            final HashMap<String, String> response = new HashMap<>();
            response.put("message", "Company created");

            return ResponseEntity.status(201).body(response);
        }catch (CompanyAlreadyExists e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
