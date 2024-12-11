package com.victorpalha.vacancy_management.exceptions;

public class InvalidCredencials extends RuntimeException {
    public InvalidCredencials() {
        super("Invalid credentials");
    }
}
