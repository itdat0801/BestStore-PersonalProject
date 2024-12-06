package com.personal.dat.be.best_store_server.mapper;

import com.personal.dat.be.best_store_server.dto.request.UserCreationRequest;
import com.personal.dat.be.best_store_server.dto.request.UserUpdateRequest;
import com.personal.dat.be.best_store_server.dto.response.UserResponse;
import com.personal.dat.be.best_store_server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
