package com.example.blogwithauth.Repository;

import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <MyUser, Integer>{
    MyUser findMyUserById(Integer id);

    MyUser findMyUserByUsername(String username);

    List<MyUser> findAllByUserId(Integer id);

    Blog findMyUserById();
}
