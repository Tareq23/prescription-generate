package com.tareq23.prescription.service.impl;

import com.tareq23.prescription.dto.request.SignInRequest;
import com.tareq23.prescription.dto.request.SignUpRequest;
import com.tareq23.prescription.dto.response.JwtAuthenticationResponse;
import com.tareq23.prescription.entity.Users;
import com.tareq23.prescription.exception.InvalidCredentialException;
import com.tareq23.prescription.exception.UserAlreadyExists;
import com.tareq23.prescription.repository.UserRepository;
import com.tareq23.prescription.service.AuthenticationService;
import com.tareq23.prescription.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@Service
//@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UserAlreadyExists("Email already exists");
        }
        var user = Users.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("Invalid email or password"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new InvalidCredentialException("Invalid email or password");
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
