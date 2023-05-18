package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private int inStock;

    @OneToMany(mappedBy = "stock")
    private Collection<Shop> shops;

    @OneToMany(mappedBy = "stock")
    private Collection<Products> products;

}
