package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.ShopResource;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.repository.ShopRepository;
import com.example.shopmanagement.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.ShopMapper.SHOP_MAPPER;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    @Override
    public Collection<ShopResource> findAll() {
        return SHOP_MAPPER.toShopResources(shopRepository.findAll());
    }

    @Override
    public Optional<ShopResource> findById(Long id) {
        return shopRepository.findById(id).map(SHOP_MAPPER::toShopResource);
    }

    @Override
    public ShopResource create(ShopResource shop) {
        Shop savedShop = shopRepository.save(SHOP_MAPPER.fromShopResource(shop));
        shop.setId(savedShop.getId());
        return shop;
    }

    @Override
    public ShopResource update(ShopResource shop, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}
