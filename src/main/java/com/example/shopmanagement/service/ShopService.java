package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.ShopResource;

import java.util.Collection;
import java.util.Optional;

public interface ShopService {
    Collection<ShopResource> findAll();
    Optional<ShopResource> findById(Long id);
    ShopResource create(ShopResource shop);
    ShopResource update(ShopResource shop, Long id);
    void delete(Long id);
}
