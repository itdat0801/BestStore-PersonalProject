package com.personal.dat.be.best_store_server.service.serviceImpl;

import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.request.RoleRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;
import com.personal.dat.be.best_store_server.dto.response.RoleResponse;
import com.personal.dat.be.best_store_server.entity.Permission;
import com.personal.dat.be.best_store_server.entity.Role;
import com.personal.dat.be.best_store_server.mapper.PermissionMapper;
import com.personal.dat.be.best_store_server.mapper.RoleMapper;
import com.personal.dat.be.best_store_server.repository.PermissionRepository;
import com.personal.dat.be.best_store_server.repository.RoleRepository;
import com.personal.dat.be.best_store_server.service.PermissionService;
import com.personal.dat.be.best_store_server.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;


    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }
}
