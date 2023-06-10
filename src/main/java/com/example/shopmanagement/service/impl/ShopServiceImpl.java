package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.ShopResource;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.repository.EmployeesRepository;
import com.example.shopmanagement.repository.ShopRepository;
import com.example.shopmanagement.service.ShopService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.ShopMapper.SHOP_MAPPER;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final EmployeesRepository employeesRepository;
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
        try {
            Shop savedShop = shopRepository.getReferenceById(id);
            if (shop.getName() != null)
                savedShop.setName(shop.getName());
            return SHOP_MAPPER.toShopResource(shopRepository.save(savedShop));
        }
        catch (Exception e) {
            throw new EntityNotFoundException("Shop not found");
        }
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }
}
