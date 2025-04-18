package com.collegeBook.CollegeBook.controller;

import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.pojo.post.CreatePostReq;
import com.collegeBook.CollegeBook.pojo.post.EditPostReq;
import com.collegeBook.CollegeBook.service.PostService;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/create/{username}")
    public ResponseEntity<GlobalApiResponse> createPost(@RequestBody @Valid CreatePostReq createPostReq, @PathVariable String username){
        return successResponse(StringConstant.POST_CREATED,postService.createPost(username,createPostReq));
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<GlobalApiResponse> getAllPosts(){
        return successResponse(StringConstant.ALL_POSTS,postService.getAllPosts());
    }


    @PostMapping("/edit/{username}/{postId}")
    public ResponseEntity<GlobalApiResponse> editPost(@RequestBody @Valid EditPostReq editPostReq, @PathVariable Long postId, @PathVariable String username){
        return successResponse(StringConstant.POST_UPDATED,postService.editPost(username,editPostReq,postId));
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<GlobalApiResponse> deletePost(@PathVariable String username,@PathVariable Long id){

        return successResponse(StringConstant.POST_DELETED,postService.deletePost(username,id));
    }



}
