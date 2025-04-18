package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.constant.StringConstant;
import com.collegeBook.CollegeBook.entity.Role;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.auth.SignInRequest;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import com.collegeBook.CollegeBook.pojo.user.ChangePasswordReq;
import com.collegeBook.CollegeBook.pojo.user.UserResponse;
import com.collegeBook.CollegeBook.repository.RoleRepository;
import com.collegeBook.CollegeBook.repository.UserRepository;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String signUp(SignUpRequest signUpRequest) throws AppException {

        validateUserName(signUpRequest.getUserName());
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(signUpRequest.getPassword());

    Role role = roleRepository.findByName("USER").orElseThrow(()->new AppException("Role Not Found"));

    user.getRoles().add(role);
        userRepository.save(user);
        return "User Registered Successfully";
    }

    @Override
    public String signIn(SignInRequest signInRequest) {

        User user = userRepository.findByUserName(signInRequest.getUserName()).orElseThrow(()-> new AppException("User not found"));
        if(user.getPassword().equals(signInRequest.getPassword())){
            return "Login SuccessFul";
        }else{
            throw new AppException("Incorrect Username or password");
        }

    }

    @Override
    public UserResponse getUser(String username) {

        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("User not found"));

        List<String> roles = user.getRoles().stream().map(role->role.getName()).collect(Collectors.toList());
        return new UserResponse(user.getId(),user.getFirstName(),user.getLastName(),user.getUserName(),roles);
    }

    @Override
    public String changePassword(String username, ChangePasswordReq changePasswordReq) {

        User user = (userRepository.findByUserName(username).orElseThrow(() -> new AppException("User Not Found ")));

        if(!user.getPassword().equals(changePasswordReq.getOldPassword())){
           throw new AppException("Old Password didn't Match");
        }else if(!changePasswordReq.getNewPassword().equals(changePasswordReq.getConfirmPassword())){

            throw new AppException("New Password and Confirm Password didn't Match");
        }else{
            user.setPassword(changePasswordReq.getNewPassword());
            userRepository.save(user);
            return "Password Changed";
        }
    }

    @Override
    public String deleteUser(String username) {

        Boolean a =userRepository.findUserWithRoleAdminOrModerator(username);

        if(userRepository.findUserWithRoleAdminOrModerator(username)){
            throw new AppException("Admin or Moderator cannot be deleted");
        }


        User user= userRepository.findByUserName(username).orElseThrow(()->new AppException("user not found"));
        userRepository.delete(user);
        return "User with Id "+user.getId()+" deleted Successfully";
    }

    @Override
    public List<UserResponse> getUsers() {
       List<User> users = userRepository.findAll();
       List<UserResponse> userResponses = new ArrayList<>();
       for(User user : users){

           List<String> roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

           userResponses.add(new UserResponse(user.getId(),user.getFirstName(),user.getLastName(),user.getUserName(),roles));
       }

        return userResponses;
    }

    @Override
    public String makeModerator(String username) {

        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("User not found"));

        Role role = roleRepository.findByName(RoleEnum.MODERATOR.name()).orElseThrow(()->new AppException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return StringConstant.MADE_MODERATOR;
    }

    @Override
    public String removeModerator(String username) {

        User user = userRepository.findByUserName(username).orElseThrow(()->new AppException("User not found"));

        Role role = roleRepository.findByName(RoleEnum.MODERATOR.name()).orElseThrow(()->new AppException("Role not found"));

        user.getRoles().remove(role);
        userRepository.save(user);
        return StringConstant.REMOVE_MODERATOR;
    }

    @Override
    public List<UserResponse> getModerators(String roleName) {

        List<User> moderators = userRepository.getModerators(RoleEnum.MODERATOR.name());

        List<UserResponse> userResponses = new ArrayList<>();

        for(User user:moderators){

            List<String> roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

            userResponses.add(new UserResponse(user.getId(),user.getFirstName(),user.getLastName(),user.getUserName(),roles));
        }
        return userResponses;
    }


    public void validateUserName(String username) throws AppException {

        Optional<User> user = userRepository.findByUserName(username);

        if(user.isPresent()){
            throw new AppException("Username Already Exists");
        }
    }
}

