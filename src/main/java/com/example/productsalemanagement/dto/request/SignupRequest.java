package com.example.productsalemanagement.dto.request;


import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {

    private String userName;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String email;
}
