package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoriesMapper.class, StockMapper.class})
public interface ProductsMapper {
    ProductsMapper PRODUCTS_MAPPER = Mappers.getMapper(ProductsMapper.class);
    @Mapping(target = "stock.id", source = "ProductsResource.stockId")
    Products fromProductsResource(ProductsResource ProductsResource);
    @Mapping(target = "stockId", source = "Products.stock.id")
    ProductsResource toProductsResource(Products Products);
}
