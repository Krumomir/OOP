package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeesResource {
    private Long id;
    private String name;
    private String role;
    private Long salary;
    private Long shopId;
    private Date createdDate;
}
