package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.auth.SignInRequest;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import com.collegeBook.CollegeBook.pojo.user.ChangePasswordReq;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    String signUp(@Valid SignUpRequest signUpRequest) throws AppException;

    String  signIn(@Valid SignInRequest signInRequest);

    UserResponse getUser();

    String changePassword(ChangePasswordReq changePasswordReq);

    String deleteUser(String username);

    List<UserResponse> getUsers();

    String makeModerator(String username);

    String removeModerator(String username);

    List<UserResponse> getModerators(String roleName);


}
