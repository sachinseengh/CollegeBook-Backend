package com.collegeBook.CollegeBook.repository;


import com.collegeBook.CollegeBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUserName(String userName);

    @Query(value = "SELECT COUNT(*) > 0 FROM users u " +
            "JOIN  user_roles ur ON u.id = ur.user_id " +
            "JOIN roles r ON r.id = ur.role_id " +
            "WHERE u.user_name = :username " +
            "AND (r.name = 'ADMIN' OR r.name='MODERATOR') ", nativeQuery = true)
    Boolean findUserWithRoleAdminOrModerator(@Param("username") String username);


    @Query(value ="SELECT * FROM users u " +
            "JOIN user_roles ur ON u.id = ur.user_id " +
            "JOIN roles r ON r.id = ur.role_id " +
            "WHERE r.name =:role", nativeQuery = true)
    List<User> getModerators(@Param("role") String roleName);


}
