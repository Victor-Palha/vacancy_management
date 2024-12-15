package com.victorpalha.vacancy_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Vacancy Management",
                description = "API to connect candidates to companies",
                version = "1.0.0"
        )
)
@SecurityScheme(
    name = "jwt_auth", scheme = "bearer", bearerFormat = "jwt", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER
)
public class VacancyManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacancyManagementApplication.class, args);
    }

}
