package com.example.shopmanagement.controller.resources;

import lombok.Data;

import java.util.Collection;

@Data
public class StockResource {
    private Long id;
    private int inStock;
    private Collection<ProductsResource> products;
    private Collection<ShopResource> shops;
}
