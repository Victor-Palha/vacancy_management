package com.victorpalha.vacancy_management.modules.company.repository;

import com.victorpalha.vacancy_management.modules.candidate.dto.FindJobByFilterRequestDTO;
import com.victorpalha.vacancy_management.modules.company.entities.JobEntity;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {
    public static Specification<JobEntity> filterByRequest(FindJobByFilterRequestDTO filter) {
        return (root, query, criteriaBuilder) -> {
            Specification<JobEntity> specification = Specification.where(null);

            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                specification = specification.and((root1, query1, cb) ->
                        cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
            }

            if (filter.getLocation() != null && !filter.getLocation().isEmpty()) {
                specification = specification.and((root1, query1, cb) ->
                        cb.like(cb.lower(root.get("location")), "%" + filter.getLocation().toLowerCase() + "%"));
            }

            if (filter.getSeniority() != null && !filter.getSeniority().isEmpty()) {
                specification = specification.and((root1, query1, cb) ->
                        cb.like(cb.lower(root.get("level")), "%" + filter.getSeniority().toLowerCase() + "%"));
            }

            if (filter.isRemote()) {
                specification = specification.and((root1, query1, cb) ->
                        cb.equal(root.get("remote"), filter.isRemote()));
            }

            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}
