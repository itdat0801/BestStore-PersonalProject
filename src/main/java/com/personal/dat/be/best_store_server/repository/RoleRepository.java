package com.personal.dat.be.best_store_server.repository;

import com.personal.dat.be.best_store_server.entity.Permission;
import com.personal.dat.be.best_store_server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
