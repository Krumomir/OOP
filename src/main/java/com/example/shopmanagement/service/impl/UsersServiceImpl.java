package com.example.shopmanagement.service.impl;

import com.example.shopmanagement.controller.resources.ProductsResource;
import com.example.shopmanagement.controller.resources.UsersResource;
import com.example.shopmanagement.entity.Products;
import com.example.shopmanagement.entity.Users;
import com.example.shopmanagement.repository.ProductsRepository;
import com.example.shopmanagement.repository.UsersRepository;
import com.example.shopmanagement.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static com.example.shopmanagement.mapper.UsersMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    @Override
    public Collection<UsersResource> findAll() {
        return USER_MAPPER.toUsersResources(usersRepository.findAll());
    }

    @Override
    public Optional<UsersResource> findById(Long id) {
        return usersRepository.findById(id).map(USER_MAPPER::toUsersResource);
    }

    @Override
    public UsersResource create(UsersResource users) {
        Users savedUsers = usersRepository.save(USER_MAPPER.fromUsersResource(users));
        users.setId(savedUsers.getId());
        users.setUsername(savedUsers.getUsername());
        users.setPassword(savedUsers.getPassword());
        users.setRole(savedUsers.getRole());

        return users;
    }

    @Override
    public UsersResource update(UsersResource users, Long id) {
        try {
            Users savedUsers = usersRepository.getReferenceById(id);
            if (users.getUsername() != null)
                savedUsers.setUsername(users.getUsername());
            if (users.getPassword() != null)
                savedUsers.setPassword(users.getPassword());
            if (users.getRole() != null)
                savedUsers.setRole(users.getRole());

            if (users.getProducts() != null) {
                for (ProductsResource productResource : users.getProducts()) {
                    Products existingProduct = productsRepository.findByName(productResource.getName())
                            .orElse(null);
                    if (!(existingProduct == null))
                        savedUsers.getProducts().add(existingProduct);
                }
            }
            else
                savedUsers.setProducts(new ArrayList<>());

            return USER_MAPPER.toUsersResource(usersRepository.save(savedUsers));
        } catch (Exception e) {
            throw new EntityNotFoundException("Users not found");
        }
    }

    @Override
    public void delete(Long id) {
        Users users = usersRepository.getReferenceById(id);
        users.getProducts().forEach(product ->
                product.getUsers().remove(users));
        usersRepository.deleteById(id);
    }
}
