package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.ProductRequestDto;
import com.example.productsalemanagement.entity.Category;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.CategoryRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


//    @Override
//    public Product saveProduct(ProductRequestDto productRequestDto) {
//
//
//        return productRepository.save(product);
//    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id)));
        return product;
    }

    @Override
    public List<Product> addProduct(ProductRequestDto product) {
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found")));

        Product saveProduct = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .importDate(new Date(System.currentTimeMillis()))
                .enabled(product.isEnabled())
                .category(category.get())
                .build();
        System.out.println(saveProduct);
        productRepository.save(saveProduct);
        System.out.println("saved");

        return getAllProducts();
    }

    @Override
    public List<Product> deleteProduct(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not exist with id: " + id)));
        productRepository.delete(product.get());
        return getAllProducts();
    }

    @Override
    public Product updateProduct(ProductRequestDto productRequestDto) {
        System.out.println("update product");
        Optional<Product> product = Optional.ofNullable(productRepository.findById(productRequestDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist")));

        product.get().setName((productRequestDto.getName()));
        product.get().setDescription(productRequestDto.getDescription());
        product.get().setImage(productRequestDto.getImage());
        product.get().setPrice(productRequestDto.getPrice());
        product.get().setQuantity(productRequestDto.getQuantity());
        product.get().setEnabled(productRequestDto.isEnabled());
        System.out.println(product);
        Product savedProduct = productRepository.save(product.get());
        System.out.println("saved");

        return savedProduct;
    }


}

