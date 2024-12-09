package com.victorpalha.vacancy_management.modules.company.controllers;

import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import com.victorpalha.vacancy_management.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final CreateJobUseCase createJobUseCase;
    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> execute(@Valid @RequestBody JobEntity job) {
        try {
            this.createJobUseCase.execute(job);
            final HashMap<String, String> response = new HashMap<>();
            response.put("message", "Job created successfully");

            return ResponseEntity.status(201).body(response);
        }catch (Exception error) {
            return ResponseEntity.status(500).body(error);
        }
    }
}
