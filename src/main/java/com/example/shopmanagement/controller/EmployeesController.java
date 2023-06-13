package com.example.shopmanagement.controller;

import com.example.shopmanagement.controller.resources.EmployeesResource;
import com.example.shopmanagement.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeesResource resource) {
        EmployeesResource saved = service.create(resource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/employees/{id}").buildAndExpand(saved.getId()).toUri()
        ).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody EmployeesResource resource) {
        return ResponseEntity.ok(service.update(resource, id));
    }

    @GetMapping("/{id}/audits")
    public ResponseEntity<?> getAllAudits(@PathVariable long id) {
        return ResponseEntity.ok(service.findAllAudits(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
