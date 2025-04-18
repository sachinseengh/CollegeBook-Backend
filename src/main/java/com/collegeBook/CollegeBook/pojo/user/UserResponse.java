package com.collegeBook.CollegeBook.pojo.user;

import com.collegeBook.CollegeBook.entity.Role;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String userName;
    private List<String> role;
}
