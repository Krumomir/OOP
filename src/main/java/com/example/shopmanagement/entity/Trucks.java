package com.example.shopmanagement.entity;

import com.example.shopmanagement.mapper.ShopMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Trucks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "trucks")
    private Collection<Shop> shops;
}
