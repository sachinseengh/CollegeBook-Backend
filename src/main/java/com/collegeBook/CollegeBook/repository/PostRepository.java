package com.collegeBook.CollegeBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collegeBook.CollegeBook.entity.Posts;

public interface PostRepository extends JpaRepository<Posts,Long> {
}
