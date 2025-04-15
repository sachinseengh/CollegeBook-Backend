package com.collegeBook.CollegeBook.InitialDataLoader;

import com.collegeBook.CollegeBook.entity.Role;
import com.collegeBook.CollegeBook.enums.RoleEnum;
import com.collegeBook.CollegeBook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
            loadRoles();
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
}
