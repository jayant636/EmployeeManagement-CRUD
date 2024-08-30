package com.Week2Demo1.Structure1.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data // So that we can use getter & setter everywhere
@Builder
public class ApiError {

    private HttpStatus status;

    private String message;
}
