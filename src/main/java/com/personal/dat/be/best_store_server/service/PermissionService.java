package com.personal.dat.be.best_store_server.service;

import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createcreatePermission(PermissionRequest request);
    List<PermissionResponse> getAllPermissions();
    void deletePermission(String permission);
}
