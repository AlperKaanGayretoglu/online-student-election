package com.alpergayretoglu.online_student_election.service;

import com.alpergayretoglu.online_student_election.model.entity.ObsUser;
import com.alpergayretoglu.online_student_election.model.entity.User;
import com.alpergayretoglu.online_student_election.model.enums.UserRole;
import com.alpergayretoglu.online_student_election.model.request.auth.LoginRequest;
import com.alpergayretoglu.online_student_election.model.response.AuthenticationResponse;
import com.alpergayretoglu.online_student_election.repository.ObsUserRepository;
import com.alpergayretoglu.online_student_election.repository.UserRepository;
import com.alpergayretoglu.online_student_election.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository; // TODO: make this userService
    private final ObsUserRepository obsUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(LoginRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            return register(request);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            throw new RuntimeException("Invalid Email"); // TODO: specific exception
        });

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }

        throw new RuntimeException("Invalid Password"); // TODO specific exception
    }

    private AuthenticationResponse register(LoginRequest request) {
        ObsUser obsUser = obsUserRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            throw new RuntimeException("Invalid Email"); // TODO: specific exception
        });

        User user = User.builder()
                .name(obsUser.getName())
                .surname(obsUser.getSurname())
                .email(obsUser.getEmail())
                .password(passwordEncoder.encode(obsUser.getPassword())) // hash password
                .role(UserRole.VOTER)
                .build();

        User response = userRepository.save(user);
        String jwtToken = jwtService.generateToken(response);
        return new AuthenticationResponse(jwtToken);
    }


}