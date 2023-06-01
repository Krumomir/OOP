package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.EmployeesResource;
import com.example.shopmanagement.entity.Employees;
import com.example.shopmanagement.repository.EmployeesRepository;
import com.example.shopmanagement.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.EmployeesMapper.EMPLOYEES_MAPPER;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeesRepository employeesRepository;

    @Override
    public Collection<EmployeesResource> findAll() {
        return EMPLOYEES_MAPPER.toEmployeesResources(employeesRepository.findAll());
    }

    @Override
    public Optional<EmployeesResource> findById(Long id) {
        return employeesRepository.findById(id).map(EMPLOYEES_MAPPER::toEmployeesResource);
    }

    @Override
    public EmployeesResource create(EmployeesResource employees) {
        Employees savedEmployees = employeesRepository.save(EMPLOYEES_MAPPER.fromEmployeesResource(employees));
        employees.setId(savedEmployees.getId());
        return employees;
    }

    @Override
    public EmployeesResource update(EmployeesResource employees, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
          employeesRepository.deleteById(id);
    }
}
