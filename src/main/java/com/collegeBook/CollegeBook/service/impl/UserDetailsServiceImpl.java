package com.collegeBook.CollegeBook.service.impl;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.exception.AppException;
import com.collegeBook.CollegeBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional // Needed to avoid LazyInitializationException for roles
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(
                        user.getRoles()
                                .stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
