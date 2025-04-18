package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.pojo.post.CreatePostReq;
import com.collegeBook.CollegeBook.pojo.post.EditPostReq;
import com.collegeBook.CollegeBook.pojo.post.PostResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PostService {

    String createPost(String username, @RequestBody @Valid CreatePostReq createPostReq);
    String editPost(String username, @RequestBody @Valid EditPostReq editPostReq, Long post_id);
    String deletePost(String username,@PathVariable Long post_id);
    List<PostResponse> getAllPosts();
}
