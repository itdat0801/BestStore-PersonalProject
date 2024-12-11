package com.personal.dat.be.best_store_server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import com.personal.dat.be.best_store_server.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
/**
 * Author: Nguyễn Tiến Đạt
 * Target: This is config class for Authentication Entry Point,
 * It used in method FilterChain, result is a json value when authentication failed
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

        response.setStatus(errorCode.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiRespronse<?> apiRespronse = ApiRespronse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiRespronse));
        response.flushBuffer();
    }
}
