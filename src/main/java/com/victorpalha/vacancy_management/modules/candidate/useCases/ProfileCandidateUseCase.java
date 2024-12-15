package com.victorpalha.vacancy_management.modules.candidate.useCases;

import com.victorpalha.vacancy_management.exceptions.CandidateNotFound;
import com.victorpalha.vacancy_management.modules.candidate.dto.CandidateProfileResponseDTO;
import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import com.victorpalha.vacancy_management.modules.candidate.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    final private CandidateRepository candidateRepository;
    public ProfileCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateProfileResponseDTO execute(String userId){
        Optional<CandidateEntity> candidateExists = this.candidateRepository.findById(UUID.fromString(userId));
        if (candidateExists.isEmpty()){
            throw new CandidateNotFound();
        }
        new CandidateProfileResponseDTO();

        return CandidateProfileResponseDTO.builder()
                .email(candidateExists.get().getEmail())
                .description(candidateExists.get().getDescription())
                .name(candidateExists.get().getName())
                .curriculum(candidateExists.get().getCurriculum())
                .username(candidateExists.get().getUsername())
                .build();
    }
}
