package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.StockResource;
import com.example.shopmanagement.entity.Stock;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(uses = {ShopMapper.class, ProductsMapper.class})
public interface StockMapper {
    StockMapper STOCK_MAPPER = Mappers.getMapper(StockMapper.class);
    Stock fromStockResource(StockResource stockResource);
    StockResource toStockResource(Stock stock);
    Collection<StockResource> toStockResources(Collection<Stock> stock);
}
