package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class ShopResource {
    private Long id;
    private String name;
    private Collection<EmployeesResource> employees;
    private Collection<TrucksResource> trucks;
    private Collection<ProductsResource> products;
}
