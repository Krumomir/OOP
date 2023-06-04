package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.controller.resources.ShopResource;
import com.example.shopmanagement.entity.Categories;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.entity.Trucks;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(uses = {EmployeesMapper.class, TrucksMapper.class})
public interface ShopMapper {
    ShopMapper SHOP_MAPPER = Mappers.getMapper(ShopMapper.class);
    @Mapping(target = "stock.id", source = "shopResource.stockId")
    Shop fromShopResource(ShopResource shopResource);
    @InheritInverseConfiguration
    ShopResource toShopResource(Shop shop);
    Collection<ShopResource> toShopResources(Collection<Shop> shops);

}
