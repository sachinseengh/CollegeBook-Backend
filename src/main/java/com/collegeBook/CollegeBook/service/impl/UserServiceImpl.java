package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.entity.Role;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.pojo.auth.SignInRequest;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import com.collegeBook.CollegeBook.pojo.user.ChangePasswordReq;
import com.collegeBook.CollegeBook.repository.RoleRepository;
import com.collegeBook.CollegeBook.repository.UserRepository;
import com.collegeBook.CollegeBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            return "Login Failed";
        }

    }

    @Override
    public User getUser(String username) {

        Optional<User> user = userRepository.findByUserName(username);
        return user.orElse(null);
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

        User user= userRepository.findByUserName(username).orElseThrow(()->new AppException("user not found"));
        userRepository.delete(user);
        return "User with Id "+user.getId()+" deleted Successfully";
    }

    public void validateUserName(String username) throws AppException {

        Optional<User> user = userRepository.findByUserName(username);

        if(user.isPresent()){
            throw new AppException("Username Already Exists");
        }
    }
}

