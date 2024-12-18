package com.victorpalha.vacancy_management.modules.candidate.controllers;

import com.victorpalha.vacancy_management.exceptions.CandidateNotFound;
import com.victorpalha.vacancy_management.exceptions.UserAlreadyExists;
import com.victorpalha.vacancy_management.modules.candidate.dto.CandidateProfileResponseDTO;
import com.victorpalha.vacancy_management.modules.candidate.dto.FindJobByFilterRequestDTO;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.useCases.CreateCandidateUseCase;
import com.victorpalha.vacancy_management.modules.candidate.useCases.FindJobByFilterUseCase;
import com.victorpalha.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;
    private final ProfileCandidateUseCase profileCandidateUseCase;
    private final FindJobByFilterUseCase findJobByFilterUseCase;
    public CandidateController(CreateCandidateUseCase createCandidateUseCase,
                               ProfileCandidateUseCase profileCandidateUseCase, FindJobByFilterUseCase findJobByFilterUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
        this.profileCandidateUseCase = profileCandidateUseCase;
        this.findJobByFilterUseCase = findJobByFilterUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){
        try{
            this.createCandidateUseCase.execute(candidate);
            final HashMap<String, String> response = new HashMap<>();
            response.put("message", "Candidate created");

            return ResponseEntity.status(201).body(response);
        }catch (UserAlreadyExists e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request){
        final String candidateId = request.getAttribute("entity_id").toString();
        try{
            CandidateProfileResponseDTO candidate = this.profileCandidateUseCase.execute(candidateId);
            final HashMap<String, CandidateProfileResponseDTO> response = new HashMap<>();
            response.put("data", candidate);

            return ResponseEntity.status(200).body(response);
        } catch (CandidateNotFound error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidate", description = "Information to candidate")
    @Operation(summary = "Find jobs provide by companies", description = "This endpoint providers all jobs available " +
            "to candidates based on search filters")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> getJobs(@RequestBody FindJobByFilterRequestDTO findJobByFilterRequestDTO){

        final List<JobEntity> jobs = this.findJobByFilterUseCase.execute(findJobByFilterRequestDTO);
        final HashMap<String, List<JobEntity>> response = new HashMap<>();
        response.put("data", jobs);
        return ResponseEntity.status(200).body(response);
    }
}
