package com.personal.dat.be.best_store_server.service;

import com.personal.dat.be.best_store_server.dto.request.UserCreationRequest;
import com.personal.dat.be.best_store_server.dto.request.UserUpdateRequest;
import com.personal.dat.be.best_store_server.dto.response.UserResponse;
import com.personal.dat.be.best_store_server.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public UserResponse createRequest(UserCreationRequest request);
    public List<UserResponse> getAllUsers();
    public UserResponse getUserById(String id);
    public UserResponse updateUser(String UserId, UserUpdateRequest request);
    public void deleteUser(String UserId);
    public UserResponse getMyInfo();
}
