package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.entity.Posts;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.post.CreatePostReq;
import com.collegeBook.CollegeBook.pojo.post.EditPostReq;
import com.collegeBook.CollegeBook.repository.PostRepository;
import com.collegeBook.CollegeBook.repository.UserRepository;
import com.collegeBook.CollegeBook.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public String createPost(String username, CreatePostReq createPostReq) {
        Posts post = new Posts();
        post.setCaption(createPostReq.getCaption());
        if(createPostReq.getContent() != null || createPostReq.getContent() == ""){
            post.setContent(createPostReq.getContent());
        }else{
            post.setCaption(null);
        }
        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("User not Found"));
        post.setUser(user);
        post.setDate(LocalDateTime.now());
        postRepository.save(post);
        return StringConstant.POST_CREATED;
    }

    @Override
    public String editPost(String username, EditPostReq editPostReq, Long post_id) {

        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("User not found"));
        Posts post = (Posts) user.getPosts().stream().filter(x->x.getId().equals(post_id)).findFirst().orElseThrow(()->new AppException("Post not found"));

        if(editPostReq.getContent()!=null || editPostReq.getContent()==""){
            post.setContent(editPostReq.getContent());
        }
        if(editPostReq.getCaption()!=null || editPostReq.getCaption()==""){
            post.setCaption(editPostReq.getCaption());
        }
        postRepository.save(post);

        return StringConstant.POST_UPDATED;
    }

    @Override
    public String deletePost(String username, Long post_id) {

        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("Username not found"));

        Posts post =user.getPosts().stream().filter(x->x.getId().equals(post_id)).findFirst().orElseThrow(()->new AppException("Post not found"));

        user.getPosts().remove(post);
        post.setUser(null);
        userRepository.save(user);
        return StringConstant.POST_DELETED;
    }


}
