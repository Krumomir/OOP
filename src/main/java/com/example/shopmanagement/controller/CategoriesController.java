package com.example.shopmanagement.controller;

import com.example.shopmanagement.controller.resources.CategoriesResource;
import com.example.shopmanagement.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService service;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoriesResource resource) {
        CategoriesResource saved = service.create(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/categories/{id}").buildAndExpand(saved.getId()).toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody CategoriesResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
