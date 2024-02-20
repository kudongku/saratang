package com.example.saratang.exception;

import com.example.saratang.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<CommonResponseDto> handleException(IllegalArgumentException ex) {
        CommonResponseDto commonResponseDto = new CommonResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                commonResponseDto,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<CommonResponseDto> nullPointerExceptionHandler(NullPointerException ex) {
        CommonResponseDto commonResponseDto = new CommonResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                // HTTP body
                commonResponseDto,
                // HTTP status code
                HttpStatus.NOT_FOUND
        );
    }
}
