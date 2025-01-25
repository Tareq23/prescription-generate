package com.tareq23.prescription.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<?> invalidCredential(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<?> duplicateEmail(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getLocalizedMessage());
    }

}
