package com.example.productsalemanagement.dto.response;

import com.example.productsalemanagement.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;
    private Date importDate;
    private int expiredDate;
    private boolean enabled;
    private String origin;
    private Category category;
    private int orderQuantity;
}
