package com.collegeBook.CollegeBook.repository;

import com.collegeBook.CollegeBook.entity.User;
import com.collegeBook.CollegeBook.pojo.post.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import com.collegeBook.CollegeBook.entity.Posts;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts,Long> {

  Optional<List<Posts>> findAllByUser(User user);

  @Query(value="select * from posts p order by p.id desc",nativeQuery = true)
  List<Posts> getPosts();

  List<Posts> findAllByUserOrderByDateDesc(User user);
}
