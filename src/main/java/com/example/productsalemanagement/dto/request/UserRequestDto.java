package com.example.productsalemanagement.dto.request;

import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.Role;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

public class UserRequestDto {
    private Long id;

    private String userName;
    private String password;
    private String fullName;

    private String address;
    private String phone;
    private String email;
    private boolean enabled;
    private Role role;
    private Set<Order> orders;
}
