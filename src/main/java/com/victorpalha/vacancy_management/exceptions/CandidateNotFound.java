package com.victorpalha.vacancy_management.exceptions;

public class CandidateNotFound extends RuntimeException {
    public CandidateNotFound() {
        super("Candidate not found");
    }
}
