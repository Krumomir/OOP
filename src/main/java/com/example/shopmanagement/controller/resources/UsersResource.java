package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class UsersResource {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Collection<ProductsResource> products;
}
