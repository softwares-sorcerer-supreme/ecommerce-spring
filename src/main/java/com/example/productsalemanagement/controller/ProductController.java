package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.request.ProductRequestDto;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<List<Product>> addProduct(@RequestBody @Valid ProductRequestDto product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductRequestDto product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
