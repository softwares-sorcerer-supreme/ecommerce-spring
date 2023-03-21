package com.example.productsalemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_date")
    private Date importDate;

    @Column(name = "expired")
    private int expiredDate;

    @Column(name = "origin")
    private String origin;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<CartProduct> cartProducts;

    @Builder
    public Product(String name, String description, String image, Date importDate, double price, int quantity, int expiredDate, boolean enabled, Category category, String origin) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.importDate = importDate;
        this.expiredDate = expiredDate;
        this.enabled = enabled;
        this.category = category;
        this.origin = origin;
    }
}
