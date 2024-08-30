package com.Week2Demo1.Structure1.advices;

import com.Week2Demo1.Structure1.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
       ApiError apierror = ApiError
                           .builder()
                           .status(HttpStatus.NOT_FOUND)
                           .message("Resource Not Found")
                           .build();
       return buildErrorResponseEntity(apierror);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apierror = ApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apierror);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apierror) {
        return new ResponseEntity<>(new ApiResponse<>(apierror),apierror.getStatus());
    }

}
