package com.example.productsalemanagement.service;

import com.example.productsalemanagement.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User getUser(Long id);

    List<User> addUser(User user);

    User updateUser(User user);

    List<User> deleteUser(Long id);
}
