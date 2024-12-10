package com.personal.dat.be.best_store_server.controller;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.dto.request.UserCreationRequest;
import com.personal.dat.be.best_store_server.dto.request.UserUpdateRequest;
import com.personal.dat.be.best_store_server.dto.response.UserResponse;
import com.personal.dat.be.best_store_server.entity.User;
import com.personal.dat.be.best_store_server.exception.AppException;
import com.personal.dat.be.best_store_server.exception.ErrorCode;
import com.personal.dat.be.best_store_server.mapper.UserMapper;
import com.personal.dat.be.best_store_server.repository.RoleRepository;
import com.personal.dat.be.best_store_server.repository.UserRepository;
import com.personal.dat.be.best_store_server.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping
    ApiRespronse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiRespronse<UserResponse> apiRespronse = new ApiRespronse<>();
        apiRespronse.setResult(userService.createRequest(request));
        return apiRespronse;
    }

    @GetMapping
    ApiRespronse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info("Roles : {}", grantedAuthority.getAuthority()));

        return ApiRespronse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }
    @GetMapping("/{userId}")
    ApiRespronse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiRespronse.<UserResponse>builder()
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiRespronse<UserResponse> getUser() {
        return ApiRespronse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiRespronse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {

        return ApiRespronse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();

    }
    @DeleteMapping("/{userId}")
    ApiRespronse deleteUser(@PathVariable("userId") String userId) {
        String result ;
        userService.deleteUser(userId);
        return ApiRespronse.builder()
                .message( "User has been deleted")
                .build();
    }
}
