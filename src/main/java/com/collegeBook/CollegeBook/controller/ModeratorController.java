package com.collegeBook.CollegeBook.controller;


import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.service.PostService;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/moderator")
public class ModeratorController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    @GetMapping("/getUsers")
    public ResponseEntity<GlobalApiResponse> getAllUsers(){
        return successResponse(StringConstant.USERS_FETCHED,userService.getUsers());
    }
    @DeleteMapping("/delete-user/{username}")
    public ResponseEntity<GlobalApiResponse> deleteUser(@PathVariable String username){
        return successResponse(StringConstant.USER_DELETED,userService.deleteUser(username));
    }

    @DeleteMapping("/delete/delete-post/{username}/{post_id}")
    public ResponseEntity<GlobalApiResponse> deletePost(@PathVariable String username,Long post_id){
        return successResponse(StringConstant.POST_DELETED,postService.deletePost(post_id));
    }
}
