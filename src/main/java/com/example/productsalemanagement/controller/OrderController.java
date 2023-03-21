package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.response.OrderDetailResponse;
import com.example.productsalemanagement.dto.response.OrderResponse;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<List<OrderResponse>> getListOrderByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getListOrderByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/{orderId}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailByOrderId(@PathVariable Long userId,
                                                                       @PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderDetailByOrderId(userId, orderId), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<SuccessResponse> createOrder(@RequestParam Long cartId,
                                                       @RequestParam Long userId,
                                                       @RequestParam String address) {
        return new ResponseEntity<>(orderService.createOrder(cartId, userId, address), HttpStatus.CREATED);
    }



}
