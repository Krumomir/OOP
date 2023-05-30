package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.entity.Trucks;
import org.mapstruct.Mapper;

@Mapper(uses = {ShopMapper.class})
public interface TrucksMapper {
    TrucksMapper TRUCKS_MAPPER = org.mapstruct.factory.Mappers.getMapper(TrucksMapper.class);
    Trucks fromTrucksResource(TrucksResource trucksResource);
    TrucksResource toTrucksResource(Trucks trucks);
}
