package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.CategoriesResource;
import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.repository.CategoriesRepository;
import com.example.shopmanagement.repository.ProductsRepository;
import com.example.shopmanagement.service.CategoriesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.CategoriesMapper.CATEGORIES_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;
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
            Categories savedCategories = categoriesRepository.getReferenceById(id);
            if (categories.getName() != null)
                savedCategories.setName(categories.getName());

            if (categories.getProducts() != null)
            {
                for (ProductsResource productResource : categories.getProducts()) {
                    Products existingProduct = productsRepository.findByName(productResource.getName())
                            .orElse(null);
                    if (!(existingProduct == null))
                        savedCategories.getProducts().add(existingProduct);
                }
            }
            else
                savedCategories.setProducts(new ArrayList<>());


            return CATEGORIES_MAPPER.toCategoriesResource(categoriesRepository.save(savedCategories));
        } catch (Exception e) {
            throw new EntityNotFoundException("Categories not found");
        }
       /* try {
            Categories savedCategories = categoriesRepository.getReferenceById(id);
            if (categories.getName() != null)
                savedCategories.setName(categories.getName());

            savedCategories.getProducts().clear();

            for (ProductsResource productResource : categories.getProducts()) {
                Products existingProduct = productsRepository.findByName(productResource.getName())
                        .orElse(null);
                if (!(existingProduct == null))
                    savedCategories.getProducts().add(existingProduct);
            }

            return CATEGORIES_MAPPER.toCategoriesResource(categoriesRepository.save(savedCategories));
        } catch (Exception e) {
            throw new EntityNotFoundException("Categories not found");
        }*/
    }

    @Override
    public void delete(Long id) {
     /*   Categories category = categoriesRepository.getReferenceById(id);
        category.getProducts().forEach(product ->
                product.getCategories().remove(category));*/
        categoriesRepository.deleteById(id);
    }
}