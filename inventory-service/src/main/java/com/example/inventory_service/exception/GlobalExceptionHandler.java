package com.example.inventory_service.exception;

import com.example.inventory_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceUnavailable(
            ProductServiceUnavailableException exception,
            HttpServletRequest request
    ){

        ErrorResponse response=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "SERVICE_UNAVAILABLE",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }

//    @ExceptionHandler(ProductServiceUnavailableException.class)
//    public ResponseEntity<ErrorResponse> InsufficientStockException(
//            InsufficientStockException exception,
//            HttpServletRequest request
//    ){
//
//        ErrorResponse response=new ErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.NOT_ACCEPTABLE.value(),
//                "STOCK_INSUFFICIENT",
//                exception.getMessage(),
//                request.getRequestURI()
//        );
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_ACCEPTABLE)
//                .body(response);
//    }

}
