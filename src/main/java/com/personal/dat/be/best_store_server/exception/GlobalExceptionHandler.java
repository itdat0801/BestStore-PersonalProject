package com.personal.dat.be.best_store_server.exception;

import com.personal.dat.be.best_store_server.dto.request.ApiRespronse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRespronse> handleRuntimeException(RuntimeException e) {
        ApiRespronse apiRespronse = new ApiRespronse();
        apiRespronse.setCode(101);
        apiRespronse.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(apiRespronse);
    }
    //Result uncategorized exception not expected
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespronse> handleException(Exception e) {
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
        return ResponseEntity.badRequest().body(apiRespronse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespronse> handlingValidation(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();

        // if key enum not correct
        ErrorCode errorCode= ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
        }

        ApiRespronse apiRespronse = new ApiRespronse();
        apiRespronse.setCode(errorCode.getCode());
        apiRespronse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespronse);
    }

}
