package com.collegeBook.CollegeBook.pojo.auth;



import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class SignInRequest {

    @NotNull(message = "username is required")
    @NotEmpty
    private String userName;

    @NotEmpty
    @NotNull(message ="password is required")
    private String password;
}
