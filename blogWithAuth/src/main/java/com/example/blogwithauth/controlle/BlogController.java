package com.example.blogwithauth.controlle;

import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Service.BlogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/all-blogs")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getall());
    }
    @GetMapping("/my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getMyBlog(myUser.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity getBlogById(Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogById(id , myUser.getId()));
    }
    @PostMapping("/add-blog")
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog, @AuthenticationPrincipal MyUser myUser){
        blogService.addMyBlog(blog,myUser.getId());
        return ResponseEntity.status(201).body(("blog Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog, Integer id, @AuthenticationPrincipal MyUser myUser){
        blogService.updateMyBlog(id,blog,myUser.getId());
        return ResponseEntity.status(200).body("blog Updated"));
    }
    @GetMapping("/{title}")
    public ResponseEntity getBlogByTitle(String title , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogBytitle(title , myUser.getId()));
    }
}
