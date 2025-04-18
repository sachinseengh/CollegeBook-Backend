package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.entity.Posts;
import com.collegeBook.CollegeBook.pojo.post.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void getAllPosts(){
      List<PostResponse> posts=  postService.getAllPosts();

    }
}
