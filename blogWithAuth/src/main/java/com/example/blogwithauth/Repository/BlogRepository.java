package com.example.blogwithauth.Repository;

import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findAllByUserId(Integer userId);

    Blog findBlogById(Integer id);

    List findAllByMyUser(MyUser myUser);

    Blog findBlogByTitle(String title);
}
