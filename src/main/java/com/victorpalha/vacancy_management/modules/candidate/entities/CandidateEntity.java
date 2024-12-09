package com.victorpalha.vacancy_management.modules.candidate.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;
@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    @Pattern(regexp = "\\S+", message = "The field [USERNAME] should not contain spaces!")
    private String username;
    @Email(message = "The field [EMAIL] should be a valid email!")
    private String email;
    @Length(min = 6, max = 20)
    private String password;
    @Length(min = 20, max = 350)
    private String description;
    @URL()
    private String curriculum;
}
