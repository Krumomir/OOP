package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.ProductsResource;

import java.util.Collection;
import java.util.Optional;

public interface ProductsService {
    Collection<ProductsResource> findAll();
    Optional<ProductsResource> findById(Long id);
    ProductsResource create(ProductsResource products);
    ProductsResource update(ProductsResource products, Long id);
    void delete(Long id);
}
