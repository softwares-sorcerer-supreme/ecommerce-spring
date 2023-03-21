package com.example.productsalemanagement.dto.response;

import com.example.productsalemanagement.entity.OrderDetail;
import com.example.productsalemanagement.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponse {

    private Long id;
    private Date createdDate;
    private Double amount;
    private int totalItem;
    private String address;
    private String message;
    private String code;

}
