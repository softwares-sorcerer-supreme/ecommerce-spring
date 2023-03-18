package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.SignupRequest;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.dto.response.UserResponse;
import com.example.productsalemanagement.entity.Cart;
import com.example.productsalemanagement.entity.Role;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.BadRequestException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.CartRepository;
import com.example.productsalemanagement.repository.RoleRepository;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserResponse signing(String username, String password) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username or password is invalid not found!"));

        if (!user.isEnabled())
            throw new BadRequestException("This account has been locked!");

        if (!password.equals(user.getPassword()))
            throw new BadRequestException("Username or password is invalid not found!");

        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .cartId(user.getCart().getId())
                .role(user.getRole().getId())
                .build();
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

        Cart cart = cartRepository.save(new Cart());

        User user = User.builder()
                .userName(signupRequest.getUserName())
                .password(signupRequest.getPassword())
                .fullName(signupRequest.getFullName())
                .address(signupRequest.getAddress())
                .phone(signupRequest.getPhone())
                .email(signupRequest.getEmail())
                .enabled(true)
                .role(role)
                .cart(cart)
                .build();

        userRepository.save(user);

        return new SuccessResponse(HttpStatus.OK.value(), "Signup successfully!");
    }
}
