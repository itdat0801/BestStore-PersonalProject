package com.personal.dat.be.best_store_server.exception;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: Nguyễn Tiến Đạt
 * Target: Handle ALl exception
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    //Result uncategorized exception not expected
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespronse> handleRuntimeException(RuntimeException e) {
        log.error("Exception: ",e);

        ApiRespronse apiRespronse = new ApiRespronse();
                apiRespronse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
                apiRespronse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiRespronse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespronse> handleAppException(AppException e) {
        ErrorCode errorCode= e.getErrorCode();
        ApiRespronse apiRespronse = new ApiRespronse();
        apiRespronse.setCode(errorCode.getCode());
        apiRespronse.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiRespronse);
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiRespronse> handleAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiRespronse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespronse> handlingValidation(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        Map<String, Object> attributes = null;
        // if key enum not correct
        ErrorCode errorCode= ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
            var constraintViolation = e.getBindingResult()
                    .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            log.info(attributes.toString());
        } catch (IllegalArgumentException ex) {
        }

        ApiRespronse apiRespronse = new ApiRespronse();
        apiRespronse.setCode(errorCode.getCode());
        apiRespronse.setMessage(Objects.nonNull(attributes) ?
                mapAttribute(errorCode.getMessage(),attributes)
                : errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespronse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();

        return message.replace("{"+MIN_ATTRIBUTE+"}", minValue);
    }
}
