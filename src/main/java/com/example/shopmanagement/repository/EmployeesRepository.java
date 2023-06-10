package com.example.shopmanagement.repository;

import com.example.shopmanagement.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    boolean existsById (Long id);
}
