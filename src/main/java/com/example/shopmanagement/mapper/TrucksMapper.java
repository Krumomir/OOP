package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.entity.Trucks;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(uses = {ShopMapper.class})
public interface TrucksMapper {
    TrucksMapper TRUCKS_MAPPER = org.mapstruct.factory.Mappers.getMapper(TrucksMapper.class);
    default Trucks fromTrucksResource(TrucksResource trucksResource)
    {
        Trucks trucks = new Trucks();
        trucks.setId(trucksResource.getId());
        trucks.setName(trucksResource.getName());

        for(String shop : trucksResource.getShops())
        {
            Shop shop1 = new Shop();
            shop1.setName(shop);
            trucks.getShops().add(shop1);
        }

        return trucks;
    }
    default TrucksResource toTrucksResource(Trucks trucks)
    {
        TrucksResource trucksResource = new TrucksResource();
        trucksResource.setId(trucks.getId());
        trucksResource.setName(trucks.getName());

        for(Shop shop : trucks.getShops())
        {
            trucksResource.getShops().add(shop.getName());
        }

        return trucksResource;
    }

    Collection<TrucksResource> toTrucksResources(Collection<Trucks> trucks);
}
