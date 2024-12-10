package com.personal.dat.be.best_store_server.service.serviceImpl;

import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;
import com.personal.dat.be.best_store_server.entity.Permission;
import com.personal.dat.be.best_store_server.mapper.PermissionMapper;
import com.personal.dat.be.best_store_server.repository.PermissionRepository;
import com.personal.dat.be.best_store_server.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;


    @Override
    public PermissionResponse createcreatePermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }


}
