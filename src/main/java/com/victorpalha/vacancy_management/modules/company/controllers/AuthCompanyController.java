package com.victorpalha.vacancy_management.modules.company.controllers;

import com.victorpalha.vacancy_management.exceptions.InvalidCredencials;
import com.victorpalha.vacancy_management.modules.company.dto.AuthCompanyDTO;
import com.victorpalha.vacancy_management.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;
    public AuthCompanyController(AuthCompanyUseCase authCompanyUseCase) {
        this.authCompanyUseCase = authCompanyUseCase;
    }

    @PostMapping("/company")
    public ResponseEntity<Object> authCompany(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try{
            final String token = this.authCompanyUseCase.execute(authCompanyDTO);
            final HashMap<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (InvalidCredencials e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
