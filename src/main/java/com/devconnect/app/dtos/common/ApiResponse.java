package com.devconnect.app.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private LocalDateTime timestamp;
    private T data;

    public ApiResponse(String message, Boolean success, T data) {
        this.message = message;
        this.success = success;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message, true, null);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(message, true, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, false, null);
    }
}
