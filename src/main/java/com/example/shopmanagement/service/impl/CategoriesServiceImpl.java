package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.CategoriesResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.repository.CategoriesRepository;
import com.example.shopmanagement.service.CategoriesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.CategoriesMapper.CATEGORIES_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public Collection<CategoriesResource> findAll() {
        return CATEGORIES_MAPPER.toCategoriesResources(categoriesRepository.findAll());
    }

    @Override
    public Optional<CategoriesResource> findById(Long id) {
        return categoriesRepository.findById(id).map(CATEGORIES_MAPPER::toCategoriesResource);
    }

    @Override
    public CategoriesResource create(CategoriesResource categories) {
        Categories savedCategories = categoriesRepository.save(CATEGORIES_MAPPER.fromCategoriesResource(categories));
        categories.setId(savedCategories.getId());
        return categories;
    }

    @Override
    public CategoriesResource update(CategoriesResource categories, Long id) {
        try {
            Categories savedCategories = categoriesRepository.save(CATEGORIES_MAPPER.fromCategoriesResource(categories));
            categories.setName(savedCategories.getName());
            return categories;
        } catch (Exception e) {
            throw new EntityNotFoundException("Categories not found");
        }
    }

    @Override
    public void delete(Long id) {
        categoriesRepository.deleteById(id);
    }
}
