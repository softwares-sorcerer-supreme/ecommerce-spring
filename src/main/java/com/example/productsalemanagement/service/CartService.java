package com.example.productsalemanagement.service;

import com.example.productsalemanagement.dto.response.ProductCartResponse;
import com.example.productsalemanagement.dto.response.ProductResponseDTO;
import com.example.productsalemanagement.dto.response.SuccessResponse;

import java.util.List;

public interface CartService {
    List<ProductCartResponse> getProductsByCart(Long id);

    SuccessResponse addProductToCart(Long id, Long productId, int quantity);

    List<ProductCartResponse> deleteProduct(Long id, Long productId);

    List<ProductCartResponse> updateQuantity(Long id, Long productId, int quantity);
}
