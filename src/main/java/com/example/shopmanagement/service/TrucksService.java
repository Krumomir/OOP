package com.example.shopmanagement.service;

import java.util.Collection;
import java.util.Optional;

import com.example.shopmanagement.controller.resources.TrucksResource;

public interface TrucksService {
    Collection<TrucksResource> findAll();
    Optional<TrucksResource> findById(Long id);
    TrucksResource create(TrucksResource trucks);
    TrucksResource update(TrucksResource trucks, Long id);
    void delete(Long id);
}
