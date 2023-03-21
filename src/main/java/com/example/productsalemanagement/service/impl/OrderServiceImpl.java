package com.example.productsalemanagement.service.impl;

import com.example.productsalemanagement.dto.response.OrderDetailResponse;
import com.example.productsalemanagement.dto.response.OrderResponse;
import com.example.productsalemanagement.dto.response.ProductResponseDTO;
import com.example.productsalemanagement.dto.response.SuccessResponse;
import com.example.productsalemanagement.entity.*;
import com.example.productsalemanagement.exception.OutOfStockException;
import com.example.productsalemanagement.exception.ResourceNotFoundException;
import com.example.productsalemanagement.repository.*;
import com.example.productsalemanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private CartProductRepository cartProductRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderResponse> getListOrderByUser(Long userId) {
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User not ound"));
        ;
        List<Order> orderList = orderRepository.findByUser_Id(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order :
                orderList) {

            double sum = 0;
            for (OrderDetail orderDetail :
                    order.getOrderDetails()) {
                sum += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
            }

            orderResponses.add(OrderResponse.builder()
                    .id(order.getId())
                    .createdDate(order.getCreatedDate())
                    .totalItem(order.getOrderDetails().size())
                    .amount(sum)
                    .code(order.getCode())
                    .address(order.getAddress())
                    .message(order.getMessage())
                    .build());
        }


        return orderResponses;
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    @Override
    public SuccessResponse createOrder(Long cartId, Long userId, String address) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        List<CartProduct> list = cartProductRepository.findByCart_Id(cartId);

        if (list.size() == 0)
            return new SuccessResponse().builder()
                    .status(400)
                    .message("Not have any item")
                    .build();

        Order order = orderRepository.save(Order.builder()
                .address(address)
                .createdDate(new Date(System.currentTimeMillis()))
                .user(user)
                .code(getSaltString())
                .status(true)
                .build());

        list.forEach(cartProduct -> {
            Product product = productRepository.findById(cartProduct.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product " + cartProduct.getProduct().getId() + " not found!"));

            if (product.getQuantity() < cartProduct.getQuantity())
                throw new OutOfStockException("Product " + product.getName() + " has only " + product.getQuantity() + " left");

            orderDetailRepository.save(OrderDetail.builder()
                    .quantity(cartProduct.getQuantity())
                    .product(product)
                    .order(order)
                    .build());

            product.setQuantity(product.getQuantity() - cartProduct.getQuantity());
            productRepository.save(product);

            cartProductRepository.delete(cartProduct);
        });

        return new SuccessResponse().builder()
                .message("Success")
                .status(200)
                .build();
    }

    @Override
    public OrderDetailResponse getOrderDetailByOrderId(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        order.getOrderDetails().forEach(item -> {
            productResponseDTOList.add(ProductResponseDTO.builder()
                    .id(item.getProduct().getId())
                    .name(item.getProduct().getName())
                    .description(item.getProduct().getDescription())
                    .image(item.getProduct().getImage())
                    .price(item.getProduct().getPrice())
                    .quantity(item.getProduct().getQuantity())
                    .importDate(item.getProduct().getImportDate())
                    .expiredDate(item.getProduct().getExpiredDate())
                    .origin(item.getProduct().getOrigin())
                    .enabled(item.getProduct().isEnabled())
                    .category(item.getProduct().getCategory())
                    .orderQuantity(item.getQuantity())
                    .build());

        });

        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .createdDate(order.getCreatedDate())
                .totalItem(order.getOrderDetails().size())
                .address(order.getAddress())
                .message(order.getMessage())
                .code(order.getCode())
                .build();

        return OrderDetailResponse.builder()
                .order(orderResponse)
                .user(user)
                .products(productResponseDTOList)
                .build();
    }


}
