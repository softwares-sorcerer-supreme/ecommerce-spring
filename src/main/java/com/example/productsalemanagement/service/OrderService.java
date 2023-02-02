package com.example.productsalemanagement.service;


import com.example.productsalemanagement.dto.request.OrderRequestDTO;
import com.example.productsalemanagement.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getListOrderByUser(int userId);

    List<Order> createOrder(OrderRequestDTO orderRequestDTO, Long userId);
}
