package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Trucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucksRepository extends JpaRepository<Trucks, Long> {
}
