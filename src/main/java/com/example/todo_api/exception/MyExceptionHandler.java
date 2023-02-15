package com.example.todo_api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.UUID;

//@ControllerAdvice  annotation is used to handle exception across whole application, in one global handle component
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError apiError = new APIError( HttpStatus.BAD_REQUEST, "Malformed JSON request!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFound(DataNotFoundException ex){
        APIError apiError = new APIError( HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<Object> dataDuplicate(DataDuplicateException ex){
        APIError apiError = new APIError( HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgument(IllegalArgumentException ex){
        APIError apiError = new APIError( HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolation(ConstraintViolationException ex){
        APIError apiError = new APIError( HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalException(Exception ex){
        String errorCode = UUID.randomUUID().toString();
        APIError apiError = new APIError( HttpStatus.INTERNAL_SERVER_ERROR, "internal error! (code: )" + errorCode);
        //todo: add the application error data into a file - HOW(Log4J , sl4j) frameWork for file
        System.out.println("INTERNAL_ERROR " + "," + errorCode + ", " + ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    //lecture part2 (validation)


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError: fieldErrorList){
            stringBuilder.append(fieldError.getField());
            stringBuilder.append(" ");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST ,stringBuilder.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


}
