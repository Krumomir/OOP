package com.example.shopmanagement.controller.resources;

import lombok.Data;

@Data
public class EmployeesResource {
    private Long id;
    private String name;
    private String role;
    private Long salary;
    private Long shopId;
}
