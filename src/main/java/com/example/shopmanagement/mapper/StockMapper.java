package com.example.shopmanagement.mapper;

import org.mapstruct.Mapper;

@Mapper(uses = {ShopMapper.class, ProductsMapper.class})
public interface StockMapper {
}
