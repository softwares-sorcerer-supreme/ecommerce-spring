package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.request.SigninRequest;
import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signing")
    public ResponseEntity<SuccessResponse> signing(@RequestBody
                                                   @Valid SigninRequest signinRequest) {
        return new ResponseEntity(authenticationService.signing(signinRequest), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signup(@RequestBody
                                                  @Valid SignupRequest signupRequest) {
        return new ResponseEntity<>(authenticationService.signup(signupRequest), HttpStatus.OK);
    }

}
