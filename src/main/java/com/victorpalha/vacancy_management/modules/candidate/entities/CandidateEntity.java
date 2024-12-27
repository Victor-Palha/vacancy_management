package com.victorpalha.vacancy_management.modules.candidate.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Pattern(regexp = "\\S+", message = "The field [USERNAME] should not contain spaces!")
    private String username;
    @Email(message = "The field [EMAIL] should be a valid email!")
    private String email;
    @Length(min = 6)
    private String password;
    @Length(min = 20, max = 350)
    private String description;
    @URL()
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
