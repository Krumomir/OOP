package com.example.shopmanagement.service;

import com.example.shopmanagement.controller.resources.EmployeesResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeesService {
    Collection<EmployeesResource> findAll();
    Optional<EmployeesResource> findById(Long id);
    EmployeesResource create(EmployeesResource employees);
    EmployeesResource update(EmployeesResource employees, Long id);
    Collection<EmployeesResource> findAllAudits(long id);
    void delete(Long id);
}
