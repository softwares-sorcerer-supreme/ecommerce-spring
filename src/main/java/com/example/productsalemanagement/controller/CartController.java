package com.example.productsalemanagement.controller;


import com.example.productsalemanagement.dto.response.ProductCartResponse;
import com.example.productsalemanagement.dto.response.ProductResponseDTO;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductCartResponse>> getProductsByCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getProductsByCart(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> addProductToCart(@RequestParam Long id,
                                                            @RequestParam Long productId,
                                                            @RequestParam int quantity) {
        return new ResponseEntity<>(cartService.addProductToCart(id, productId, quantity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<ProductCartResponse>> deleteProduct(@PathVariable Long id,
                                                                  @RequestParam Long productId) {
        return new ResponseEntity<>(cartService.deleteProduct(id, productId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<ProductCartResponse>> updateQuantity(@RequestParam Long id,
                                                                    @RequestParam Long productId,
                                                                    @RequestParam int quantity) {
        return new ResponseEntity<>(cartService.updateQuantity(id, productId, quantity), HttpStatus.OK);
    }

}
