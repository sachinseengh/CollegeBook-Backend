package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.entity.Notes;
import com.collegeBook.CollegeBook.entity.Posts;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.post.CreatePostReq;
import com.collegeBook.CollegeBook.pojo.post.EditPostReq;
import com.collegeBook.CollegeBook.pojo.post.PostResponse;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import com.collegeBook.CollegeBook.repository.NoteRepository;
import com.collegeBook.CollegeBook.repository.PostRepository;
import com.collegeBook.CollegeBook.repository.UserRepository;
import com.collegeBook.CollegeBook.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private NoteRepository noteRepository;


    @Transactional
    @Override
    public String createPost(MultipartFile file, CreatePostReq createPostReq) throws IOException {

        System.out.println(createPostReq);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new AppException("User not Found"));



        Posts post = new Posts();
        post.setCaption(createPostReq.getCaption());
        post.setContent(createPostReq.getContent());
        post.setIsNote(createPostReq.getIsNote());


        String fileName=null;
       String fileType=null;
        if (file != null && !file.isEmpty()) {
            fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads/");
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            post.setFileName(fileName);
            post.setFileType(file.getContentType()); // Save file type

            //i will use it to set note file type
            fileType = file.getContentType();

        }


        Notes note = new Notes();
        if(createPostReq.getIsNote()){
            post.setSemester(createPostReq.getSemester());
            post.setSubject(createPostReq.getSubject());
            note.setDate(LocalDateTime.now());
            note.setSemester(createPostReq.getSemester());
            note.setSubject(createPostReq.getSubject());
            note.setFileName(fileName);
            note.setFileType(fileType);

            note.setUser(user);
            noteRepository.save(note);
        }


        post.setUser(user);
        post.setDate(LocalDateTime.now());
        postRepository.save(post);



        return StringConstant.POST_CREATED;
    }
    @Override
    public String editPost( EditPostReq editPostReq, Long post_id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUserName(authentication.getName()).orElseThrow(()->new AppException("User not found"));

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
    public String deletePost( Long post_id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUserName(authentication.getName()).orElseThrow(()->new AppException("Username not found"));

        Posts post =user.getPosts().stream().filter(x->x.getId().equals(post_id)).findFirst().orElseThrow(()->new AppException("Post not found"));

        user.getPosts().remove(post);
        post.setUser(null);
        userRepository.save(user);
        return StringConstant.POST_DELETED;
    }


    @Override
    public List<PostResponse> getAllPosts() {

        List<Posts> posts = postRepository.getPosts().orElse(Collections.emptyList());
//
        List<PostResponse> responses = new ArrayList<>();

        if (posts != null) {
            for (Posts post : posts) {

                List<String> roles = post.getUser().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

                UserResponse userResponse = new UserResponse(post.getUser()
                        .getId(), post.getUser().getFirstName(), post.getUser().getLastName(), post.getUser().getUserName(), roles);

                String fileUrl = null;
                String fileType = null;
                String fileName=null;
                if (post.getFileName() != null) {
                    fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/")
                            .path(post.getFileName())
                            .toUriString();
                    fileType = post.getFileType();
                    fileName=post.getFileName();


                }

                responses.add(new PostResponse(post.getId(), post.getCaption(), post.getContent(), post.getDate(), userResponse, fileUrl, fileType,fileName));
            }
            return responses;
        }
        return responses;

    }

    @Transactional
    @Override
    public List<PostResponse> getUserPost(String username) {

        User user = userRepository.findByUserName(username).orElseThrow(() -> new AppException("User not found"));

        List<Posts> posts = postRepository.findAllByUserOrderByDateDesc(user).orElse(Collections.emptyList());
        List<PostResponse> responses = new ArrayList<>();

        if (posts != null) {
            for (Posts post : posts) {

                List<String> roles = post.getUser().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

                UserResponse userResponse = new UserResponse(post.getUser()
                        .getId(), post.getUser().getFirstName(), post.getUser().getLastName(), post.getUser().getUserName(), roles);

                String fileUrl = null;
                String fileType = null;
                String fileName =null;

                if (post.getFileName() != null) {
                    fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/uploads/")
                            .path(post.getFileName())
                            .toUriString();
                    fileType = post.getFileType();
                }

                responses.add(new PostResponse(post.getId(), post.getCaption(), post.getContent(), post.getDate(), userResponse, fileUrl, fileType,fileName));
            }
            return responses;
        } else {
            return responses;
        }
    }
}

