package com.example.shopmanagement.controller.resources;

import lombok.Data;
import org.hibernate.sql.ast.tree.expression.Collation;

import java.util.Collection;

@Data
public class TrucksResource {
    private Long id;
    private String name;
    private Collection<String> shops;
}
