package com.collegeBook.CollegeBook.pojo.post;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponse {

    private Long post_id;
    private String caption;
    private String content;
    private LocalDateTime date;
    private UserResponse userResponse;

    private String fileUrl;
    private String fileType;
    private String fileName;

}
