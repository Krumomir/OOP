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
        Products Products = new Products();
        Products.setId(ProductsResource.getId());
        Products.setName(ProductsResource.getName());
        Products.setPrice(ProductsResource.getPrice());
        Collection<Categories> categories = null;

        for (String category : ProductsResource.getCategories()) {
            Categories categories1 = new Categories();
            categories1.setName(category);
            categories.add(categories1);
        }

        Products.setCategories(categories);
        return Products;
    }
    @Mapping(target = "stockId", source = "Products.stock.id")
    default ProductsResource toProductsResource(Products Products)
    {
        ProductsResource ProductsResource = new ProductsResource();
        ProductsResource.setId(Products.getId());
        ProductsResource.setName(Products.getName());
        ProductsResource.setPrice(Products.getPrice());
        Collection<String> categories = null;

        categories.stream().forEach(category -> {
            categories.add(category);
        });

        ProductsResource.setCategories(categories);
        return ProductsResource;
    }
    Collection<ProductsResource> toProductsResources(Collection<Products> Products);
}
