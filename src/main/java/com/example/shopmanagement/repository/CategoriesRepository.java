package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Boolean existsByName(String name);
    Optional<Categories> findByName(String name);
}
