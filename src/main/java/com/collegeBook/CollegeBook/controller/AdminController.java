package com.collegeBook.CollegeBook.controller;


import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.pojo.GlobalApiResponse;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/make-moderator/{username}")
    public ResponseEntity<GlobalApiResponse> makeAdmin(@PathVariable String username){
        return successResponse(StringConstant.MADE_MODERATOR,userService.makeModerator(username));
    }

    @DeleteMapping("/remove-moderator/{username}")
    public ResponseEntity<GlobalApiResponse> removeModerator(@PathVariable String username){
        return successResponse(StringConstant.REMOVE_MODERATOR,userService.removeModerator(username));
    }

    @GetMapping("/moderators")
    public ResponseEntity<GlobalApiResponse> getModerators(){
        return successResponse(StringConstant.GET_MODERATOR,userService.getModerators(RoleEnum.MODERATOR.name()));
    }
    
}
