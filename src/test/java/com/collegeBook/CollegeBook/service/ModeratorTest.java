package com.collegeBook.CollegeBook.service;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import com.collegeBook.CollegeBook.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ModeratorTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    @Disabled
    public void getModeratorTest(){
      List<UserResponse> response= userService.getModerators(RoleEnum.MODERATOR.name());
    }

    @Test
    public void getAdminOrModerator(){
        Boolean a = userRepository.findUserWithRoleAdminOrModerator("mangru");
    }
}
