package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.CategoriesResource;
import com.example.shopmanagement.entity.Categories;

import java.util.Collection;
import java.util.Optional;

public interface CategoriesService {
    Collection<CategoriesResource> findAll();
    Optional<CategoriesResource> findById(Long id);
    CategoriesResource create(CategoriesResource categories);
    CategoriesResource update(CategoriesResource categories, Long id);
    void delete(Long id);
}
