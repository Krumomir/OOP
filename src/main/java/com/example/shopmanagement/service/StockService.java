package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.StockResource;

import java.util.Collection;
import java.util.Optional;

public interface StockService {
    Collection<StockResource> findAll();
    Optional<StockResource> findById(Long id);
    StockResource create(StockResource stock);
    StockResource update(StockResource stock, Long id);
    void delete(Long id);
}
