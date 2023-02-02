package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.
                findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return user;
    }

    @Override
    public List<User> addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {

        return null;
    }

    @Override
    public List<User> deleteUser(Long id) {
        User user = userRepository.
                findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);

        return getUserList();
    }
}
