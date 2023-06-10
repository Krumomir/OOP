package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class ProductsResource {
private Long id;
    private String name;
    private Double price;
    private Collection<String> categories;
}
