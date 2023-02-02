package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.request.OrderRequestDTO;
import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.OrderDetail;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.entity.User;
import com.example.productsalemanagement.exception.OutOfStockException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.OrderRepository;
import com.example.productsalemanagement.repository.ProductRepository;
import com.example.productsalemanagement.repository.UserRepository;
import com.example.productsalemanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Override
    public List<Order> getListOrderByUser(int userId) {
        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        user.orElseThrow(() -> new ResourceNotFoundException("User not ound"));

        return orderRepository.findByUser_Id(Long.valueOf(userId));
    }

    @Override
    public List<Order> createOrder(OrderRequestDTO orderRequestDTO, Long userId) {
        List<Product> productOrderDetailList = new ArrayList<>();
        orderRequestDTO
                .getOrderDetailRequestDTOS()
                .forEach(ordRequest ->
                        productOrderDetailList
                                .add(productRepository
                                        .findById((long) ordRequest.getProductId())
                                        .orElseThrow(() -> new ResourceNotFoundException("Product not found"))));

        orderRequestDTO.getOrderDetailRequestDTOS().forEach(product -> {
            Product p = productRepository.findById((long) product.getProductId()).get();
            if (product.getQuantity() > p.getQuantity())
                throw new OutOfStockException("Product: " + p.getName() + " has not enough entity with " + p.getQuantity() + " left");
        });

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        Order order = Order.builder()
                .user(user)
                .orderAddress(orderRequestDTO.getAddress())
                .build();

        Set<OrderDetail> orderDetailList = new HashSet<>();
        orderRequestDTO
                .getOrderDetailRequestDTOS()
                .forEach(orderRequest -> {
                    orderDetailList.add(
                            OrderDetail.builder()
                                    .order(order)
                                    .product(productRepository
                                            .findById((long) orderRequest.getProductId()).get())
                                    .quantity(orderRequest.getQuantity())
                                    .build());
                });


        order.setOrderDetails(orderDetailList);
        orderRepository.save(order);

        return getListOrderByUser(Math.toIntExact(userId));
    }
}
