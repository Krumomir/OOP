package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.EmployeesResource;

import java.util.Collection;
import java.util.Optional;

public interface EmployeesService {
    Collection<EmployeesResource> findAll();
    Optional<EmployeesResource> findById(Long id);
    EmployeesResource create(EmployeesResource employees);
    EmployeesResource update(EmployeesResource employees, Long id);
    void delete(Long id);
}
