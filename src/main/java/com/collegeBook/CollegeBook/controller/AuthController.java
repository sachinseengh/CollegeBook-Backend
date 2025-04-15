package com.collegeBook.CollegeBook.controller;


import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.pojo.auth.SignInRequest;
import com.collegeBook.CollegeBook.pojo.auth.SignInResponse;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{

    @Autowired
    private UserService userService;


    @PostMapping("/signUp")
    public ResponseEntity<GlobalApiResponse> signUpHandler(@RequestBody @Valid SignUpRequest signUpRequest){
       return successResponse(StringConstant.SIGN_UP_SUCCESS,userService.signUp(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<GlobalApiResponse> signInHandler(@RequestBody @Valid SignInRequest signInRequest){
        return successResponse(StringConstant.SIGN_IN_SUCCESS,userService.signIn(signInRequest));
    }




}
