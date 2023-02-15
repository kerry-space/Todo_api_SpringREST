package com.example.todo_api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class APIError {
    private HttpStatus status;
    private String statusText;
    private LocalDateTime timestamp;

    public APIError(){
        this.timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status, String statusText) {
        this();
        this.status = status;
        this.statusText = statusText;
    }
}
