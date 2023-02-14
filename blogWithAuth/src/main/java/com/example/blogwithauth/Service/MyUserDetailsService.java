package com.example.blogwithauth.Service;

import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=userRepository.findMyUserByUsername(username);
        if(myUser==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }
}
