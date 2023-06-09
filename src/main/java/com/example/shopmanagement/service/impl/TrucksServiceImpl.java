package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.repository.ShopRepository;
import com.example.shopmanagement.entity.Trucks;
import com.example.shopmanagement.repository.TrucksRepository;
import com.example.shopmanagement.service.TrucksService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.TrucksMapper.TRUCKS_MAPPER;
@Service
@RequiredArgsConstructor
public class TrucksServiceImpl implements TrucksService {
    private final TrucksRepository trucksRepository;
    private final ShopRepository shopRepository;
    @Override
    public Collection<TrucksResource> findAll() {
        return TRUCKS_MAPPER.toTrucksResources(trucksRepository.findAll());
    }

    @Override
    public Optional<TrucksResource> findById(Long id) {
        return trucksRepository.findById(id).map(TRUCKS_MAPPER::toTrucksResource);
    }

    @Override
    public TrucksResource create(TrucksResource trucks) {
        Trucks savedTrucks = trucksRepository.save(TRUCKS_MAPPER.fromTrucksResource(trucks));
        trucks.setId(savedTrucks.getId());
        return trucks;
    }

    @Override
    public TrucksResource update(TrucksResource trucks, Long id) {
        try {
            Trucks savedTrucks = trucksRepository.getReferenceById(id);
            if (trucks.getName() != null)
                savedTrucks.setName(trucks.getName());

            if (trucks.getShopId() != null)
                savedTrucks.setShop(shopRepository.getReferenceById(trucks.getShopId()));

            return TRUCKS_MAPPER.toTrucksResource(trucksRepository.save(savedTrucks));
        } catch (Exception e) {
            throw new EntityNotFoundException("Trucks not found");
        }
    }

    @Override
    public void delete(Long id) {
        trucksRepository.deleteById(id);
    }
}
