package com.example.shopmanagement.entity;

import jakarta.persistence.*;

@Entity
public class Employees {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;
    private Long salary;

    @ManyToOne
    private Shop shop;
}
