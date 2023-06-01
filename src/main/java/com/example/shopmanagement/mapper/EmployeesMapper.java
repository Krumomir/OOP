package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.EmployeesResource;
import com.example.shopmanagement.entity.Employees;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface EmployeesMapper {
    EmployeesMapper EMPLOYEES_MAPPER = Mappers.getMapper(EmployeesMapper.class);
    @Mapping(target = "shop.id", source = "employeesResource.shopId")
    Employees fromEmployeesResource(EmployeesResource employeesResource);
    @InheritInverseConfiguration
    EmployeesResource toEmployeesResource(Employees employees);
    Collection<EmployeesResource> toEmployeesResources(Collection<Employees> employees);
}
