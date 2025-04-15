package com.collegeBook.CollegeBook.pojo.auth;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SignUpRequest {

    @NotNull(message = "firstName is required")
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotEmpty(message = "lastName cannot be null")
    private String lastName;

    @NotNull(message = "userName is required")
    @NotEmpty(message = "userName cannot be null")
    private String userName;

    @NotNull(message = "userName cannot be null")
    @NotEmpty(message = "userName cannot be empty")
    private String password;

}
