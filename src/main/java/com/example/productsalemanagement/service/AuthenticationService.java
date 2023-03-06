package com.example.productsalemanagement.service;

import com.example.productsalemanagement.dto.request.SigninRequest;
import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;

public interface AuthenticationService {
    SuccessResponse signing(SigninRequest signinRequest);

    SuccessResponse signup(SignupRequest signupRequest);
}
