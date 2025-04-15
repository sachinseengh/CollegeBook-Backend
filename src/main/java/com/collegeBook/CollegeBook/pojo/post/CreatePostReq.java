package com.collegeBook.CollegeBook.pojo.post;


import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreatePostReq {

    @NotEmpty(message = "Caption cannot be empty")
    @NotNull(message = "Caption cannot be null")
    private String caption;

    private String content;



}
