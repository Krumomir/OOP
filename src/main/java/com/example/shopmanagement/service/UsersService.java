package com.example.shopmanagement.service;

import java.util.Collection;
import java.util.Optional;

import com.example.shopmanagement.controller.resources.UsersResource;
import com.example.shopmanagement.entity.Users;

public interface UsersService {
    Collection<UsersResource> findAll();
    Optional<UsersResource> findById(Long id);
    UsersResource create(UsersResource users);
    UsersResource update(UsersResource users, Long id);
    void delete(Long id);
}
