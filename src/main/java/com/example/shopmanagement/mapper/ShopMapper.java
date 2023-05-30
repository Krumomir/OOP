package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ShopResource;
import com.example.shopmanagement.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {EmployeesMapper.class, TrucksMapper.class, StockMapper.class})
public interface ShopMapper {
    ShopMapper SHOP_MAPPER = Mappers.getMapper(ShopMapper.class);


}
