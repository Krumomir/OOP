package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.TrucksResource;
import com.example.shopmanagement.entity.Trucks;
import com.example.shopmanagement.repository.TrucksRepository;
import com.example.shopmanagement.service.TrucksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.TrucksMapper.TRUCKS_MAPPER;
@Service
@RequiredArgsConstructor
public class TrucksServiceImpl implements TrucksService {
    private final TrucksRepository trucksRepository;
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
        return null;
    }

    @Override
    public void delete(Long id) {
        trucksRepository.deleteById(id);
    }
}
