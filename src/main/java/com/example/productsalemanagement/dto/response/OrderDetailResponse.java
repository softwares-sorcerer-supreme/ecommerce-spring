package com.example.productsalemanagement.dto.response;

import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.Product;
import com.example.productsalemanagement.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailResponse {

    private Long id;
    private OrderResponse order;
    private User user;
    private List<ProductResponseDTO> products;


}
