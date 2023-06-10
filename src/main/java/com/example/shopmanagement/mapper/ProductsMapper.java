package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.entity.Users;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;

@Mapper(uses = {CategoriesMapper.class})
public interface ProductsMapper {
    ProductsMapper PRODUCTS_MAPPER = Mappers.getMapper(ProductsMapper.class);
    default Products fromProductsResource(ProductsResource productsResource) {
        Products products = new Products();
        products.setId(productsResource.getId());
        products.setName(productsResource.getName());
        products.setPrice(productsResource.getPrice());
        products.setCategories(new ArrayList<>());
        products.setShops(new ArrayList<>());
        products.setUsers(new ArrayList<>());

        for (String category : productsResource.getCategories()){
            Categories categories1 = new Categories();
            categories1.setName(category);
            products.getCategories().add(categories1);
        }

        if (!(productsResource.getUsers() == null))
            for (String user : productsResource.getUsers()){
                Users users1 = new Users();
                users1.setUsername(user);
                users1.setPassword(user);
                users1.setRole(user);
                products.getUsers().add(users1);
            }

        for (String shop : productsResource.getShops()){
            Shop shops1 = new Shop();
            shops1.setName(shop);
            products.getShops().add(shops1);
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
        productsResource.setShops(new ArrayList<>());
        productsResource.setUsers(new ArrayList<>());

        for (Categories category : Products.getCategories()) {
            productsResource.getCategories().add(category.getName());
        }

        if (!(Products.getUsers() == null))
            for (Users user : Products.getUsers()) {
                productsResource.getUsers().add(user.getUsername());
            }

        for (Shop shop : Products.getShops()) {
            productsResource.getShops().add(shop.getName());
        }

        return productsResource;
    }
    Collection<ProductsResource> toProductsResources(Collection<Products> Products);
}
