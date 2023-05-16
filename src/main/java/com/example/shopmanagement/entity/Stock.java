package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private int inStock;

    @OneToOne
    private Products products;

    @OneToOne
    private Shop  shop;
}
