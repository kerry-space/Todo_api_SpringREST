package com.example.todo_api.exception;

public class DataDuplicateException extends  RuntimeException {
    public DataDuplicateException(String message) {
        super(message);
    }
}
