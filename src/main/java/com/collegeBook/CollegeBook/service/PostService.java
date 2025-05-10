package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.pojo.post.CreatePostReq;
import com.collegeBook.CollegeBook.pojo.post.EditPostReq;
import com.collegeBook.CollegeBook.pojo.post.PostResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface PostService {

//    String createPost( @RequestBody @Valid CreatePostReq createPostReq);

    @Transactional
    String createPost(MultipartFile file, CreatePostReq createPostReq) throws IOException;

    String editPost(@RequestBody @Valid EditPostReq editPostReq, Long post_id);
    String deletePost(@PathVariable Long post_id);
    List<PostResponse> getAllPosts();
    List<PostResponse> getUserPost(@PathVariable String username);
}
