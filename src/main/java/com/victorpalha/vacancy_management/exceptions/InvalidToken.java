package com.victorpalha.vacancy_management.exceptions;

public class InvalidToken extends RuntimeException {
    public InvalidToken() {
        super("Invalid access token");
    }
}
