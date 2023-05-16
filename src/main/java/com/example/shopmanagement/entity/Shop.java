package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany
    private List<Employees> employees;

    @ManyToMany
    private List<Trucks> trucks;
}
