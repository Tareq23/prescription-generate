package com.tareq23.prescription.service;

import com.tareq23.prescription.dto.request.SignInRequest;
import com.tareq23.prescription.dto.request.SignUpRequest;
import com.tareq23.prescription.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
