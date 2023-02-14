package com.example.blogwithauth.Service;

import com.example.blogwithauth.Api.Api;
import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private  final UserRepository userRepository;



    public List<MyUser> getAllUsers(){
        return userRepository.findAll();
    }

    public MyUser getUser(Integer id){
        MyUser myUser=userRepository.findMyUserById(id);
        if (myUser==null){
            throw new Api("User Not Found!");
        }
        return myUser;
    }


    public void addUser(MyUser newUser){
        newUser.setRole("USER");
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
    }

    public void deleteUser(Integer id){
        MyUser myUser=userRepository.findMyUserById(id);
        if(myUser==null){
            throw new Api("User Not Found");
        }
        userRepository.delete(myUser);
    }


    public void updateUser(MyUser newUser, Integer id){
        MyUser oldUser=userRepository.findMyUserById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        userRepository.save(newUser);
    }





}
