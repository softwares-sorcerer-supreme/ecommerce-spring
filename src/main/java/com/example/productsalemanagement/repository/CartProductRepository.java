package com.example.productsalemanagement.repository;

import com.example.productsalemanagement.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findByCart_Id(Long cartId);

    CartProduct findByProduct_IdAndCart_Id(Long productId, Long cartId);
}
