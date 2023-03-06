package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.OrderRequestDTO;
import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.OrderDetail;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.OutOfStockException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.OrderDetailRepository;
import com.example.productsalemanagement.repository.OrderRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getListOrderByUser(int userId) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        user.orElseThrow(() -> new ResourceNotFoundException("User not ound"));

        return orderRepository.findByUser_Id(Long.valueOf(userId));
    }

    @Override
    public List<Order> createOrder(OrderRequestDTO orderRequestDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Order order = orderRepository.save(Order.builder()
                .address(orderRequestDTO.getAddress())
                .createdDate(new Date(System.currentTimeMillis()))
                .user(user)
                .status(true)
                .build());

        orderRequestDTO.getOrderDetail().forEach((productId, quantity) -> {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product " + productId + " not found!"));

            if (product.getQuantity() < quantity)
                throw new OutOfStockException("Product " + product.getName() + " has only " + product.getQuantity() + " left");

            orderDetailRepository.save(OrderDetail.builder()
                    .quantity(quantity)
                    .product(product)
                    .order(order)
                    .build());

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        });

        userRepository.save(user);

        return getListOrderByUser(Math.toIntExact(userId));
    }
}
