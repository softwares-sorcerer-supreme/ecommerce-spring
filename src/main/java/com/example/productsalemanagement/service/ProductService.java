package com.example.productsalemanagement.service;

import com.example.productsalemanagement.dto.request.ProductRequestDto;
import com.example.productsalemanagement.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ProductService {
//    Product saveProduct(ProductRequestDto productRequestDto);

    List<Product> getAllProducts();
    Optional<Product> getProduct(Long id);

    List<Product> addProduct(ProductRequestDto product);

    List<Product> deleteProduct(Long id);

    Product updateProduct(ProductRequestDto productRequestDto);
}

