package com.collegeBook.CollegeBook.repository;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.pojo.auth.SignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName);
}
