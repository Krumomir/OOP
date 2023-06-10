package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.repository.CategoriesRepository;

import com.example.shopmanagement.repository.CategoriesRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Mapper(uses = {CategoriesMapper.class})
public interface ProductsMapper {
    ProductsMapper PRODUCTS_MAPPER = Mappers.getMapper(ProductsMapper.class);
    default Products fromProductsResource(ProductsResource productsResource) {
        Products products = new Products();
        products.setId(productsResource.getId());
        products.setName(productsResource.getName());
        products.setPrice(productsResource.getPrice());
        products.setCategories(new ArrayList<>());

        for (String category : productsResource.getCategories()){
            Categories categories1 = new Categories();
            categories1.setName(category);
            products.getCategories().add(categories1);
        }

        return products;
    }
    default ProductsResource toProductsResource(Products Products)
    {
        ProductsResource productsResource = new ProductsResource();
        productsResource.setId(Products.getId());
        productsResource.setName(Products.getName());
        productsResource.setPrice(Products.getPrice());
        productsResource.setCategories(new ArrayList<>());

        for (Categories category : Products.getCategories()) {
            productsResource.getCategories().add(category.getName());
        }

        return productsResource;
    }
    Collection<ProductsResource> toProductsResources(Collection<Products> Products);
}
