package com.personal.dat.be.best_store_server.configuration;

import com.personal.dat.be.best_store_server.enums.Role;
import com.personal.dat.be.best_store_server.service.AuthenticationService;
import com.personal.dat.be.best_store_server.service.serviceImpl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
/**
 * Author: Nguyễn Tiến Đạt
 * Target: SecurityConfig is using config jwt of all user
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {"/auth/log-in","/auth/introspect", "/users", "/auth/token"
            ,"/auth/logout" };
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Autowired
    private CustomJwtDecoder customJwtDecoder;

    private NimbusJwtDecoder nimbusJwtDecoder = null;
    // this is an annotation filter, it is using when client sent any require with end points
    //  then it check auth and permission of this user to have been known to access or no
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeHttpRequests(request ->
               request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                       .anyRequest().authenticated());

       httpSecurity.oauth2ResourceServer(oauth2 ->
               oauth2.jwt(jwtConfigurer -> jwtConfigurer
                       .decoder(customJwtDecoder)
                       .jwtAuthenticationConverter(jwtConverter()))
                       .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
       );

       httpSecurity.csrf(AbstractHttpConfigurer:: disable);
       return httpSecurity.build();
    }
    @Bean
    JwtAuthenticationConverter jwtConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
