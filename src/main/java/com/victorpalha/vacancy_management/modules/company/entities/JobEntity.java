package com.victorpalha.vacancy_management.modules.company.entities;

import com.victorpalha.vacancy_management.modules.company.dto.CreateJobDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;


    @CreationTimestamp
    private LocalDateTime createdAt;


    public static JobEntity createFromDTO(CreateJobDTO job, Object companyId) {
        return JobEntity.builder()
                .description(job.getDescription())
                .title(job.getTitle())
                .level(job.getLevel())
                .active(job.isActive())
                .remote(job.isRemote())
                .benefits(job.getBenefits())
                .location(job.getLocation())
                .companyId(UUID.fromString(companyId.toString()))
                .build();
    }
}
