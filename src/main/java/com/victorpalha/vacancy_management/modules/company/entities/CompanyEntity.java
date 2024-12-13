package com.victorpalha.vacancy_management.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Length(min = 4, max = 50)
    private String username;
    @Email(message = "The field [EMAIL] should be a valid email!")
    private String email;
    @Length(min = 6)
    private String password;
    @URL
    private String website;
    // CNPJ for national and others to international
    private String identifier;
    @Length(min = 4, max = 50)
    private String name;
    @Length(min = 50, max = 350)
    private String description;
    private int employees;
    @URL
    private String logo;

    @CreationTimestamp
    private LocalDateTime creationDate;
}
