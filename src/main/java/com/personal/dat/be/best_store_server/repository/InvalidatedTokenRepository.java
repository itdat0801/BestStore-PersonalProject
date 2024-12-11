package com.personal.dat.be.best_store_server.repository;

import com.personal.dat.be.best_store_server.entity.InvalidatedToken;
import com.personal.dat.be.best_store_server.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
