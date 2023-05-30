package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.CategoriesResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProductsMapper.class})
public interface CategoriesMapper {
    CategoriesMapper CATEGORIES_MAPPER = Mappers.getMapper(CategoriesMapper.class);
    Categories fromCategoriesResource(CategoriesResource categoriesResource);
    CategoriesResource toCategoriesResource(Categories categories);
}
