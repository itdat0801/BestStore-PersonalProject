package com.personal.dat.be.best_store_server.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exceptions"),
    INVALID_KEY(1500, "invalid message Key"),
    USER_EXISTED(1001, "User existed"),
    USERNAME_INVALID(1002, "username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "password must be at least 8 characters");
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
