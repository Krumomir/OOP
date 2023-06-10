package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.entity.Trucks;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper
public interface TrucksMapper {
    TrucksMapper TRUCKS_MAPPER = org.mapstruct.factory.Mappers.getMapper(TrucksMapper.class);
    @Mapping(target = "shop.id", source = "trucksResource.shopId")
    Trucks fromTrucksResource(TrucksResource trucksResource);
    @InheritInverseConfiguration
    TrucksResource toTrucksResource(Trucks trucks);
    Collection<TrucksResource> toTrucksResources(Collection<Trucks> trucks);
}
