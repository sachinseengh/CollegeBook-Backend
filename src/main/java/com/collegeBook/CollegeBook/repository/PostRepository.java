package com.collegeBook.CollegeBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.collegeBook.CollegeBook.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
