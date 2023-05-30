package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class CategoriesResource {
    private Long id;
    private String name;
    private Collection<ProductsResource> products;
}
