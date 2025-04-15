package com.collegeBook.CollegeBook.controller;

import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.pojo.user.ChangePasswordReq;
import com.collegeBook.CollegeBook.repository.UserRepository;
import com.collegeBook.CollegeBook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public ResponseEntity<GlobalApiResponse> getUser(@PathVariable String username){
        return successResponse(StringConstant.USER_DETAILS,userService.getUser(username));
    }

    @PostMapping("/changePassword/{username}")
    public ResponseEntity<GlobalApiResponse> changePassword(@PathVariable String username,@RequestBody ChangePasswordReq changePasswordReq){
        return successResponse(StringConstant.PASSWORD_CHANGED,userService.changePassword(username,changePasswordReq));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<GlobalApiResponse> deleteUser(@PathVariable String username){
        return successResponse(StringConstant.USER_DELETED,userService.deleteUser(username));
    }
}
