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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController{

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/create/")
    public ResponseEntity<GlobalApiResponse> createPost(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @ModelAttribute @Valid CreatePostReq createPostReq
    ) throws IOException {
        return successResponse(StringConstant.POST_CREATED, postService.createPost(file, createPostReq));
    }


    @Transactional
    @GetMapping("/getAllPosts")
    public ResponseEntity<GlobalApiResponse> getAllPosts(){
        return successResponse(StringConstant.ALL_POSTS,postService.getAllPosts());
    }


    @PutMapping("/edit/{postId}")
    public ResponseEntity<GlobalApiResponse> editPost(@RequestBody @Valid EditPostReq editPostReq, @PathVariable Long postId){
        return successResponse(StringConstant.POST_UPDATED,postService.editPost(editPostReq,postId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalApiResponse> deletePost(@PathVariable Long id){

        return successResponse(StringConstant.POST_DELETED,postService.deletePost(id));
    }

    @GetMapping("/getUserPost/{username}")
    public ResponseEntity<GlobalApiResponse> getUserPosts(@PathVariable String username){
        return successResponse(StringConstant.USER_POST,postService.getUserPost(username));
    }


}
