package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "shop")
    private Collection<Employees> employees;

    @OneToMany(mappedBy = "shop")
    private Collection<Trucks> trucks;

    @ManyToMany(mappedBy = "shops")
    private Collection<Products> products;
}