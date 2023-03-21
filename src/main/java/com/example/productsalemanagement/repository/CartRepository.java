package com.example.productsalemanagement.repository;

import com.example.productsalemanagement.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
