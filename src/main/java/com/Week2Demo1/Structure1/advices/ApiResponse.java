package com.Week2Demo1.Structure1.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data //THIS IS IMPORTANT TO SETUP GETTER & SETTERS
public class ApiResponse <T>{

    private T data;

    private ApiError error;
    private LocalDateTime timeStamp;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }

}
