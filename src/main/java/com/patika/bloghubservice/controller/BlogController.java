package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/users/{email}")
    public Blog cretaeBlog(@RequestBody Blog blog, @PathVariable String email) {
        return blogService.createBlog(email, blog);
    }
    @GetMapping("/allBlogs")
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();

    }
    @GetMapping("/{title}")
    public Blog getBlogByTitle(@PathVariable String title){
        return blogService.getBlogByTitle(title);
    }
    @PutMapping("/{title}/users/{email}")
    public void addComment(@PathVariable String title,@PathVariable String email, @RequestBody String comment ){
         blogService.addCommets(title, email, comment);
    }

    @PutMapping("/{title}/users/{email}/like-count")
    public void addComment(@PathVariable String title,@PathVariable String email){
        blogService.likeBlog(title, email);
    }

    @GetMapping("/{title}/users/like-count")
    public Long getLikeCountByTitle(@PathVariable String title ){
       return blogService.getLikeCountByTitle(title);
    }
}