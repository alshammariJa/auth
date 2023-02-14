package com.example.blogwithauth.Controller;

import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Service.BlogService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/blog")

public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get")
    public ResponseEntity<List<Blog>> getBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogs(myUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity <Response> addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog){
        blogService.addBlogs(myUser.getId(),blog);
        return ResponseEntity.status(201).body(new Response());
    }
    @PutMapping("/{blogId}")
    public ResponseEntity <Response> updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog, @PathVariable Integer todoId){
        blogService.updateBlog(myUser.getId(),blog,todoId);
        return ResponseEntity.status(201).body(new Response());
    }




    @DeleteMapping("/{blogId}")
    public ResponseEntity removeBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer todoId){
        blogService.removeBlogs(myUser.getId(),todoId);
        return ResponseEntity.status(200).body("Todo deleted !");
    }
}
