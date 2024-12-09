package com.victorpalha.vacancy_management.modules.candidate.controllers;

import com.victorpalha.vacancy_management.modules.candidate.entities.CandidateEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @PostMapping("/")
    public void create(@RequestBody CandidateEntity candidate){
        System.out.println(candidate);
        System.out.println(candidate.getId());
    }
}
