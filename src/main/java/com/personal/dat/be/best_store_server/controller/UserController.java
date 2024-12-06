package com.personal.dat.be.best_store_server.controller;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.dto.request.UserCreationRequest;
import com.personal.dat.be.best_store_server.dto.request.UserUpdateRequest;
import com.personal.dat.be.best_store_server.dto.response.UserResponse;
import com.personal.dat.be.best_store_server.entity.User;
import com.personal.dat.be.best_store_server.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping
    ApiRespronse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiRespronse<User> apiRespronse = new ApiRespronse<>();
        apiRespronse.setResult(userService.createRequest(request));
        return apiRespronse;
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }
    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        String result ;
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
