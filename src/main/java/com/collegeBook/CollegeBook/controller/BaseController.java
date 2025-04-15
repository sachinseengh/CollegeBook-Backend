package com.collegeBook.CollegeBook.controller;

import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public ResponseEntity<GlobalApiResponse> successResponse(String message,Object data){
        GlobalApiResponse response = new GlobalApiResponse(HttpStatus.OK.value(),message,data);
        return ResponseEntity.ok(response);
    }
    public ResponseEntity<GlobalApiResponse> successResponse(String message){
        GlobalApiResponse response = new GlobalApiResponse(HttpStatus.OK.value(),message,null);
        return ResponseEntity.ok(response);
    }
}
