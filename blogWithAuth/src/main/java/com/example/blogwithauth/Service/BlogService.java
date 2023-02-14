package com.example.blogwithauth.Service;

import com.example.blogwithauth.Api.Api;
import com.example.blogwithauth.Model.Blog;
import com.example.blogwithauth.Model.MyUser;
import com.example.blogwithauth.Repository.BlogRepository;
import com.example.blogwithauth.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogService {
    private  final BlogRepository blogRepository;
  private final UserRepository userRepository;

    public List<Blog> getall(){
        return blogRepository.findAll();
    }

    public Blog getBlogById(Integer id,Integer auth){
        Blog blog=blogRepository.findBlogById(id);
        if(blog==null){
            throw new Api("blog not fond");

        }

        if(blog.getMyUser().getId()!=auth){
            throw new Api("Sorry , You do not have the authority to get this Blog!");
        }
        return blog;
    }

    public List<Blog> getMyBlog(Integer auth){
        MyUser myUser=userRepository.findMyUserById(auth);
        List blog=blogRepository.findAllByMyUser(myUser);
        if (blog.isEmpty()){
            throw new Api("Empty!");
        }
        return blog;
    }
    public void addMyBlog(Blog blog , Integer auth){
        MyUser myUser=userRepository.findMyUserById(auth);
        blog.setMyUser(myUser);

        blogRepository.save(blog);
    }

    public void updateMyBlog(Integer id , Blog newblog , Integer auth){
        Blog oldblog=blogRepository.findBlogById(id);
        MyUser myUser=userRepository.findMyUserById(auth);
        if (oldblog==null){
            throw new Api("blog not found");
        }else if(oldblog.getMyUser().getId()!=auth){
            throw new Api("Sorry ");
        }

        newblog.setId(id);
        newblog.setMyUser(myUser);

        blogRepository.save(newblog);
    }

    public void deleteMyBlog(Integer id, Integer auth){
        Blog blog=blogRepository.findBlogById(id);
        if (blog==null){
            throw new Api("Blog not found");
        }else if(blog.getMyUser().getId()!=auth){
            throw new Api("Sorry" );

        }

        blogRepository.delete(blog);
    }

    public Blog getBlogBytitle(String title,Integer auth){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new Api("blog not fond");

        }

        if(blog.getMyUser().getId()!=auth){
            throw new Api("Sorry , You do not have the authority to get this Blog!");
        }
        return blog;
    }


}
