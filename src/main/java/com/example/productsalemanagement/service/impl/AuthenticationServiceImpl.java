package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.SigninRequest;
import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.entity.Role;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.BadRequestException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.RoleRepository;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public SuccessResponse signing(SigninRequest signinRequest) {
        User user = userRepository.findByUserName(signinRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Username or password is invalid not found!"));

        if(!user.isEnabled())
            throw new BadRequestException("This account has been locked!");

        if(!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword()))
            throw new BadRequestException("Username or password is invalid not found!");

        return new SuccessResponse(HttpStatus.OK.value(), "Login successfully!");
    }

    @Override
    public SuccessResponse signup(SignupRequest signupRequest) {
        userRepository.findByUserName(signupRequest.getUserName()).ifPresent(user -> {
            throw new BadRequestException("Username has been existed!");
        });

        userRepository.findByEmail(signupRequest.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email has been existed!");
        });

        Role role = roleRepository.findById("USER").get();

        User user = User.builder()
                .userName(signupRequest.getUserName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .fullName(signupRequest.getFullName())
                .address(signupRequest.getAddress())
                .phone(signupRequest.getPhone())
                .email(signupRequest.getEmail())
                .enabled(true)
                .role(role)
                .build();

        userRepository.save(user);

        return new SuccessResponse(HttpStatus.OK.value(), "Signup successfully!");
    }
}
