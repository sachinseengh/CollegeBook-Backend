package com.collegeBook.CollegeBook.pojo.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data

public class ChangePasswordReq {


    @NotNull(message = "Old password can't be null")
    @NotEmpty(message = "Old password can't be empty")
    private String oldPassword;

    @NotNull(message = "New password can't be null")
    @NotEmpty(message = "New password can't be empty")
    private String newPassword;

    @NotNull(message = "Password Confirmation can't be null")
    @NotEmpty(message = "Password Confirmation can't be empty")
    private String confirmPassword;




}
