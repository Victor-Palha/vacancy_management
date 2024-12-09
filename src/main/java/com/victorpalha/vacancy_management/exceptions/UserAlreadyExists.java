package com.victorpalha.vacancy_management.exceptions;

public class UserAlreadyExists extends RuntimeException{
    public UserAlreadyExists(){
        super("User already exists");
    }
}
