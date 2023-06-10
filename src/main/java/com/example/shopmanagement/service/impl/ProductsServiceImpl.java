package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.repository.CategoriesRepository;
import com.example.shopmanagement.repository.ProductsRepository;
import com.example.shopmanagement.repository.ShopRepository;
import com.example.shopmanagement.service.ProductsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.ProductsMapper.PRODUCTS_MAPPER;
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;
    private final ShopRepository shopsRepository;
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
        products.getCategories()
                .removeIf(category -> !categoriesRepository.existsByName(category));

        products.getShops()
                .removeIf(shop -> !shopsRepository.existsByName(shop));

        if(products.getCategories().isEmpty())
            throw new EntityNotFoundException("Categories not found");

        if(products.getShops().isEmpty())
            throw new EntityNotFoundException("Shops not found");

        Products product = PRODUCTS_MAPPER.fromProductsResource(products);
        Collection<Categories> realCategories = new ArrayList<>();
        Collection<Shop> realShops = new ArrayList<>();

        for (Categories category : product.getCategories()) {
            Optional<Categories> categories = categoriesRepository.findByName(category.getName());
            categories.ifPresent(realCategories::add);
        }

        for (Shop shop : product.getShops()) {
            Optional<Shop> shops = shopsRepository.findByName(shop.getName());
            shops.ifPresent(realShops::add);
        }

        product.setCategories(realCategories);
        product.setShops(realShops);
        Products savedProducts = productsRepository.save(product);
        products.setId(savedProducts.getId());
        return products;
    }

    @Override
    public ProductsResource update(ProductsResource products, Long id) {
        try {
            Products savedProducts = productsRepository.getReferenceById(id);
            if(products.getName() != null)
                savedProducts.setName(products.getName());
            if(products.getPrice() != null)
                savedProducts.setPrice(products.getPrice());
            if(products.getCategories() != null) {
                products.getCategories().forEach(category -> {
                    if(categoriesRepository.existsByName(category))
                        savedProducts.getCategories().add(categoriesRepository.findByName(category).get());
                });
            }
            return PRODUCTS_MAPPER.toProductsResource(productsRepository.save(savedProducts));
        } catch (Exception e) {
            throw new EntityNotFoundException("Products not found");
        }
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }
}
