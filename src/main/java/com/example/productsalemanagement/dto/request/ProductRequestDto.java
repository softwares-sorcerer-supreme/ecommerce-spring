package com.example.productsalemanagement.dto.request;

import com.example.productsalemanagement.entity.Category;
import com.example.productsalemanagement.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto implements Serializable {
    @NotNull(message = "Product Name can not empty")
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;
    private Date expiredDate;
    private boolean enabled;

    @NotNull
    private int categoryId;
}
