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

import java.util.*;

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

            if (products.getCategories() != null) {
                Set<Categories> categoriesSet = new HashSet<>();
                for (String categoryName : products.getCategories()) {
                    Categories existingCategory = categoriesRepository.findByName(categoryName)
                            .orElse(null);
                    if (!(existingCategory == null))
                        categoriesSet.add(existingCategory);
                }
                savedProducts.setCategories(categoriesSet);
            }
            else {
                savedProducts.setCategories(null);
            }

            if(products.getShops() != null) {
                Set<Shop> shopsSet = new HashSet<>();
                for (String shopName : products.getShops()) {
                    Shop existingShop = shopsRepository.findByName(shopName)
                            .orElse(null);
                    if (!(existingShop == null))
                        shopsSet.add(existingShop);
                }
                savedProducts.setShops(shopsSet);
            }else {
                savedProducts.setShops(null);
            }

            if (products.getUsers() != null) {
                Set<Shop> shopsSet = new HashSet<>();
                for (String shopName : products.getUsers()) {
                    Shop existingShop = shopsRepository.findByName(shopName)
                            .orElse(null);
                    if (!(existingShop == null))
                        shopsSet.add(existingShop);
                }
                savedProducts.setShops(shopsSet);
            }else {
                savedProducts.setShops(null);
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
