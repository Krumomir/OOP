package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(uses = {CategoriesMapper.class})
public interface ProductsMapper {
    ProductsMapper PRODUCTS_MAPPER = Mappers.getMapper(ProductsMapper.class);
    @Mapping(target = "stock.id", source = "ProductsResource.stockId")
    default Products fromProductsResource(ProductsResource ProductsResource) {
        Products products = new Products();
        products.setId(ProductsResource.getId());
        products.setName(ProductsResource.getName());
        products.setPrice(ProductsResource.getPrice());

        for (String category : ProductsResource.getCategories()) {
            Categories categories1 = new Categories();
            categories1.setName(category);
            products.getCategories().add(categories1);
        }

        return products;
    }
    @Mapping(target = "stockId", source = "Products.stock.id")
    default ProductsResource toProductsResource(Products Products)
    {
        ProductsResource productsResource = new ProductsResource();
        productsResource.setId(Products.getId());
        productsResource.setName(Products.getName());
        productsResource.setPrice(Products.getPrice());

        for (Categories category : Products.getCategories()) {
            productsResource.getCategories().add(category.getName());
        }

        return productsResource;
    }
    Collection<ProductsResource> toProductsResources(Collection<Products> Products);
}
