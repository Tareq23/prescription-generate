package com.tareq23.prescription.controller;


import com.tareq23.prescription.dto.request.SignInRequest;
import com.tareq23.prescription.dto.request.SignUpRequest;
import com.tareq23.prescription.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;
    @PostMapping("api/v1/auth/signup")
    ResponseEntity<?> signup(@RequestBody SignUpRequest request){
        System.out.println("==================================>"+request.toString());
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("api/v1/auth/login")
    ResponseEntity<?> login(@RequestBody SignInRequest request){
        System.out.println("==================================>"+request.toString());
        return ResponseEntity.ok(authService.signin(request));
    }

}
