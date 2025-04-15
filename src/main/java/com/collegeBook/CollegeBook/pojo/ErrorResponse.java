package com.collegeBook.CollegeBook.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;
    private Object errors;

    public ErrorResponse(int statusCode, String message, long timestamp, Object errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

}