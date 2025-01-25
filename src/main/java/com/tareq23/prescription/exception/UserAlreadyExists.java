package com.tareq23.prescription.exception;

import org.springframework.security.core.userdetails.User;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String message){
        super(message);
    }
}
