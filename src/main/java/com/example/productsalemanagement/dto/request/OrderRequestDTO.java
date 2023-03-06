package com.example.productsalemanagement.dto.request;

import com.example.productsalemanagement.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Date createdDate;
    private String address;

                //product id // quantity
    private HashMap<Long, Integer> orderDetail;
}
