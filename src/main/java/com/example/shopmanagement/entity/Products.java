package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToMany
    @JoinTable(
            name = "ProductCategories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Collection<Categories> categories;
}
