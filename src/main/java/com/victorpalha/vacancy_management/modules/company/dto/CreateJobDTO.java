package com.victorpalha.vacancy_management.modules.company.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateJobDTO {
    @Length(min = 1, max = 50)
    private String title;

    @Length(min = 50, max = 500)
    private String description;

    @Length(min = 2)
    private String level;

    @Length(min = 10)
    private String benefits;

    private String location;

    private boolean active;

    private boolean remote;
}
