package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.dto.response.UserResponse;
import com.example.productsalemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signing")
    public ResponseEntity<UserResponse> signing(@RequestParam String username,@RequestParam String password) {
        return new ResponseEntity(authenticationService.signing(username, password), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signup(@RequestBody
                                                  @Valid SignupRequest signupRequest) {
        return new ResponseEntity<>(authenticationService.signup(signupRequest), HttpStatus.OK);
    }

}
