package com.personal.dat.be.best_store_server.controller;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.request.RoleRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;
import com.personal.dat.be.best_store_server.dto.response.RoleResponse;
import com.personal.dat.be.best_store_server.service.PermissionService;
import com.personal.dat.be.best_store_server.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiRespronse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiRespronse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @GetMapping
    ApiRespronse<List<RoleResponse>> findAll() {
        return ApiRespronse.<List<RoleResponse>>builder()
                .result(roleService.getRoles())
                .build();
    }
    @DeleteMapping("/{role}")
    ApiRespronse<Void> delete(@PathVariable("role") String role) {
        roleService.deleteRole(role);
        return ApiRespronse.<Void>builder().build();
    }
}
