package com.collegeBook.CollegeBook.exception;


import com.collegeBook.CollegeBook.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> appException(AppException ex){
        ex.printStackTrace();
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> errorResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponse.builder()
                        .statusCode(status.value())
                        .message(message)
                        .timestamp(System.currentTimeMillis())
                        .errors(null)
                        .build());
    }
}
