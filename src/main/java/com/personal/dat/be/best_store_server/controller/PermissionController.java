package com.personal.dat.be.best_store_server.controller;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;
import com.personal.dat.be.best_store_server.service.PermissionService;
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
@RequestMapping("/permissions")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiRespronse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiRespronse.<PermissionResponse>builder()
                .result(permissionService.createcreatePermission(request))
                .build();
    }

    @GetMapping
    ApiRespronse<List<PermissionResponse>> findAllPermissions() {
        return ApiRespronse.<List<PermissionResponse>>builder()
                .result(permissionService.getAllPermissions())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiRespronse<Void> deletePermission(@PathVariable("permission") String permission) {
        permissionService.deletePermission(permission);
        return ApiRespronse.<Void>builder().build();
    }
}
