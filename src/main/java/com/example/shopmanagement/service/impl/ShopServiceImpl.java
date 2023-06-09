package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.EmployeesResource;
import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.controller.resources.ShopResource;
import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.entity.Employees;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.entity.Shop;
import com.example.shopmanagement.entity.Trucks;
import com.example.shopmanagement.repository.EmployeesRepository;
import com.example.shopmanagement.repository.ProductsRepository;
import com.example.shopmanagement.repository.ShopRepository;
import com.example.shopmanagement.repository.TrucksRepository;
import com.example.shopmanagement.service.ShopService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.ShopMapper.SHOP_MAPPER;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final EmployeesRepository employeesRepository;
    private final ProductsRepository productsRepository;
    private final TrucksRepository trucksRepository;
    private final EntityManagerFactory entityManager;
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

            if (shop.getProducts() != null) {
                for (ProductsResource productResource : shop.getProducts()) {
                    Products existingProduct = productsRepository.findByName(productResource.getName())
                            .orElse(null);
                    if (!(existingProduct == null))
                        savedShop.getProducts().add(existingProduct);
                }
            }
            else
                savedShop.setProducts(new ArrayList<>());

            if (shop.getEmployees() != null) {
                for (EmployeesResource employeeResource : shop.getEmployees()) {
                    Employees existingEmployee = employeesRepository.findByName(employeeResource.getName())
                            .orElse(null);
                    if (!(existingEmployee == null))
                        savedShop.getEmployees().add(existingEmployee);
                }
            }
            else
                savedShop.setEmployees(new ArrayList<>());

            if (shop.getTrucks() != null) {
                for (TrucksResource truckResource : shop.getTrucks()) {
                    Trucks existingTruck = trucksRepository.findByName(truckResource.getName())
                            .orElse(null);
                    if (!(existingTruck == null))
                        savedShop.getTrucks().add(existingTruck);
                }
            }
            else
                savedShop.setTrucks(new ArrayList<>());

            return SHOP_MAPPER.toShopResource(shopRepository.save(savedShop));
        }
        catch (Exception e) {
            throw new EntityNotFoundException("Shop not found");
        }
    }

    @Override
    public Collection<ShopResource> findAllAudits(long id) {
        AuditReader reader = AuditReaderFactory.get(entityManager.createEntityManager());
        return SHOP_MAPPER.toShopResources(reader.createQuery()
                .forRevisionsOfEntity(Shop.class, true, true)
                .add(AuditEntity.id().eq(id))
                .getResultList());
    }


    @Override
    public void delete(Long id) {
        Shop shop = shopRepository.getReferenceById(id);
        shop.getProducts().forEach(product ->
            product.getShops().remove(shop));
        shopRepository.deleteById(id);
    }
}
