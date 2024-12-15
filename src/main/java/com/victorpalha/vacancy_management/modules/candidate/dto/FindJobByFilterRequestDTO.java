package com.victorpalha.vacancy_management.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindJobByFilterRequestDTO {
    private String description;
    private boolean remote;
    private String seniority;
    private String location;
}
