package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.auth.SignInRequest;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import com.collegeBook.CollegeBook.pojo.user.ChangePasswordReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface UserService {

    String signUp(@Valid SignUpRequest signUpRequest) throws AppException;

    String  signIn(@Valid SignInRequest signInRequest);

    User getUser(@PathVariable String username);

    String changePassword(String username, ChangePasswordReq changePasswordReq);

    String deleteUser(String username);
}
