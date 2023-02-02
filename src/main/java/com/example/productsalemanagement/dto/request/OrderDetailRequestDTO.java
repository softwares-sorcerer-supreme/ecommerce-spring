package com.example.productsalemanagement.dto.request;

import com.example.productsalemanagement.entity.Order;
import com.example.productsalemanagement.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDTO {
    private int productId;
    private int quantity;

}
