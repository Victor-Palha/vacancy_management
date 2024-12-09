package com.victorpalha.vacancy_management.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Column(name = "company_id")
    private UUID companyId;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;


    @CreationTimestamp
    private LocalDateTime createdAt;
}
