package com.collegeBook.CollegeBook.InitialDataLoader;

import com.collegeBook.CollegeBook.entity.Role;
import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.repository.RoleRepository;
import com.collegeBook.CollegeBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
            loadRoles();
            loadAdminUser();
    }

    private void loadRoles(){

        for(RoleEnum roleEnum : RoleEnum.values()){
            roleRepository.findByName(roleEnum.name()).orElseGet(()->{
                Role role = new Role();
                role.setName(roleEnum.name());
                return roleRepository.save(role);
            });


        }
    }
    public void loadAdminUser(){

        if(!userRepository.findByUserName("sachin").isPresent()){
            Role role = roleRepository.findByName(RoleEnum.ADMIN.name()).orElseThrow(()->new AppException("Role not Found"));
            User user = new User();
            user.setFirstName("Sachin");
            user.setLastName("Singh");
            user.setUserName("sachin");
            user.setPassword("sachin");
            user.getRoles().add(role);
            userRepository.save(user);
        }

    }
}
