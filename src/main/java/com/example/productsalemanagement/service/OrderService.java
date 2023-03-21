package com.example.productsalemanagement.service;


import com.example.productsalemanagement.dto.response.OrderDetailResponse;
import com.example.productsalemanagement.dto.response.OrderResponse;
import com.example.productsalemanagement.dto.response.SuccessResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getListOrderByUser(Long userId);

    SuccessResponse createOrder(Long cartId, Long userId, String address);

    OrderDetailResponse getOrderDetailByOrderId(Long userId, Long orderId);
}
