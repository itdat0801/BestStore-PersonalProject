package com.personal.dat.be.best_store_server.configuration;

import com.personal.dat.be.best_store_server.entity.Role;
import com.personal.dat.be.best_store_server.entity.User;
import com.personal.dat.be.best_store_server.repository.RoleRepository;
import com.personal.dat.be.best_store_server.repository.UserRepository;
import com.personal.dat.be.best_store_server.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nguyễn Tiến Đạt
 * Target: Config Annotation when application start
 */
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleRepository RoleRepository;
    // Create User with Role Admin when you start application
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                Set<Role> roles = new HashSet<>();
                Role adminRole = RoleRepository.findById("ADMIN").get();
                roles.add(adminRole);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
