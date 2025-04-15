package com.collegeBook.CollegeBook.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlobalApiResponse {

    private int statusCode;
    private String message;
    private Object data;
}
