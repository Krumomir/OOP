package com.example.shopmanagement.mapper;

import com.example.shopmanagement.controller.resources.UsersResource;
import com.example.shopmanagement.entity.Users;
import com.example.shopmanagement.repository.UsersRepository;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductsMapper.class})
public interface UsersMapper {
    UsersMapper USER_MAPPER = org.mapstruct.factory.Mappers.getMapper(UsersMapper.class);
    Users fromUsersResource(UsersResource users);
    UsersResource toUsersResource(Users users);
    Collection<UsersResource> toUsersResources(List<Users> users);
}
