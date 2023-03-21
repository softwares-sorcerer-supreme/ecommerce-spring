package com.example.productsalemanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductCartResponse {

    private Long productId;
    private String productName;
    private double price;
    private String imgUrl;
    private int quantity;

}
