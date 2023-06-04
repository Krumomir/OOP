package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.StockResource;
import com.example.shopmanagement.entity.Stock;
import com.example.shopmanagement.repository.StockRepository;
import com.example.shopmanagement.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.StockMapper.STOCK_MAPPER;
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    @Override
    public Collection<StockResource> findAll() {
        return STOCK_MAPPER.toStockResources(stockRepository.findAll());
    }

    @Override
    public Optional<StockResource> findById(Long id) {
        return stockRepository.findById(id).map(STOCK_MAPPER::toStockResource);
    }

    @Override
    public StockResource create(StockResource stock) {
        Stock savedStock = stockRepository.save(STOCK_MAPPER.fromStockResource(stock));
        stock.setId(savedStock.getId());
        return stock;
    }

    @Override
    public StockResource update(StockResource stock, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }
}
