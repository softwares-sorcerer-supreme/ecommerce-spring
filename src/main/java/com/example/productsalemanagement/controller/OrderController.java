package com.example.productsalemanagement.controller;

import com.example.productsalemanagement.dto.request.OrderRequestDTO;
import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.repository.OrderRepository;
import com.example.productsalemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<List<Order>> getListOrderByUser(@PathVariable int userId) {
        return new ResponseEntity<>(orderService.getListOrderByUser(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<List<Order>> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO, @PathVariable Long userId) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO, userId), HttpStatus.CREATED);
    }

}
