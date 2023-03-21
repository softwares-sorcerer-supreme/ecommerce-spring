package com.example.productsalemanagement.service;

import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.dto.response.UserResponse;
import com.example.productsalemanagement.entity.User;

public interface AuthenticationService {
    UserResponse signing(String username, String password);

    SuccessResponse signup(SignupRequest signupRequest);
}
