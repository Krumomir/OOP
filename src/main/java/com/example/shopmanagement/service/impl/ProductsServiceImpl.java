package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.repository.ProductsRepository;
import com.example.shopmanagement.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.ProductsMapper.PRODUCTS_MAPPER;
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    @Override
    public Collection<ProductsResource> findAll() {
        return PRODUCTS_MAPPER.toProductsResources(productsRepository.findAll());
    }

    @Override
    public Optional<ProductsResource> findById(Long id) {
        return productsRepository.findById(id).map(PRODUCTS_MAPPER::toProductsResource);
    }

    @Override
    public ProductsResource create(ProductsResource products) {
        Products savedProducts = productsRepository.save(PRODUCTS_MAPPER.fromProductsResource(products));
        products.setId(savedProducts.getId());
        return products;
    }

    @Override
    public ProductsResource update(ProductsResource products, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }
}
