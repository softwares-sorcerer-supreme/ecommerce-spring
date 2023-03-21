package com.example.productsalemanagement.dto.response;

import com.example.productsalemanagement.entity.Cart;
import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse implements Serializable {

    private Long id;

    private String userName;

    private String password;

    private String fullName;

    private String address;

    private String phone;

    private String email;

    private boolean enabled;

    private Long cartId;
    private String role;


}
