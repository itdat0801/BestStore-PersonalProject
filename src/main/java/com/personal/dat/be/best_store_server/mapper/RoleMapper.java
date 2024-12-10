package com.personal.dat.be.best_store_server.mapper;

import com.personal.dat.be.best_store_server.dto.request.PermissionRequest;
import com.personal.dat.be.best_store_server.dto.request.RoleRequest;
import com.personal.dat.be.best_store_server.dto.response.PermissionResponse;
import com.personal.dat.be.best_store_server.dto.response.RoleResponse;
import com.personal.dat.be.best_store_server.entity.Permission;
import com.personal.dat.be.best_store_server.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
