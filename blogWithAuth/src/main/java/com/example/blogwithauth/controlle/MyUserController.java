package com.example.blogwithauth.controlle;

import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

public class MyUserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body(("Logged in successfully"));
    }
    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        return ResponseEntity.status(200).body(userService.getUser(id));
    }
    @GetMapping("/my-user")
    public ResponseEntity getMyUser(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(200).body(userService.getUser(auth.getId()));
    }

    public ResponseEntity register(@Valid @RequestBody MyUser newUser){
        userService.addUser(newUser);
        return ResponseEntity.status(201).body(("User Created"));
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid MyUser newUser, @AuthenticationPrincipal MyUser auth){
        userService.updateUser(newUser , auth.getId());
        return ResponseEntity.status(200).body(("User Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(("User Deleted"));
    }
}
