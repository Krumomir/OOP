package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Employees {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;
    private Long salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
