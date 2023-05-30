package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.EmployeesResource;
import com.example.shopmanagement.entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ShopMapper.class})
public interface EmployeesMapper {
    EmployeesMapper EMPLOYEES_MAPPER = Mappers.getMapper(EmployeesMapper.class);
    @Mapping(target = "shop.id", source = "EmployeesResource.shopId")
    Employees fromEmployeesResource(EmployeesResource EmployeesResource);
    @Mapping(target = "shopId", source = "Employees.shop.id")
    EmployeesResource toEmployeesResource(Employees Employees);
}
