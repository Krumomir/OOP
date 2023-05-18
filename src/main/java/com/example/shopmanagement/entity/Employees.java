package com.example.shopmanagement.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Employees {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;
    private Long salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopId")
    private Shop shop;
}
