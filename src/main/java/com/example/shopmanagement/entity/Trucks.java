package com.example.shopmanagement.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Trucks {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "trucks")
    private Collection<Shop> shops;
}
