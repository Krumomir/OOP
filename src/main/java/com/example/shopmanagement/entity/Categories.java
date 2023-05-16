package com.example.shopmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    private List<Products> products;
}