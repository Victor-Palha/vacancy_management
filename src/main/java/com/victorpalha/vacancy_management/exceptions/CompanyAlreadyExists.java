package com.victorpalha.vacancy_management.exceptions;

public class CompanyAlreadyExists extends RuntimeException {
    public CompanyAlreadyExists() {
        super("Company with these information already exists");
    }
}
