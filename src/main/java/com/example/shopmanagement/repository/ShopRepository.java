package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByName (String name);
    Optional<Shop> findByName (String name);
}
