package com.patika.bloghubservice.service;

import com.patika.bloghubservice.model.BlogComment;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.model.enums.BlogStatus;
import com.patika.bloghubservice.repository.BlogRepository;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;

    public Blog createBlog(String email, Blog requestBlog) {

        User foundUser = userService.getUserByEmail(email);

        Blog blog = new Blog(requestBlog.getTitle(), requestBlog.getText(), foundUser);

        blogRepository.save(blog);

        return blog;

    }

    public Blog getBlogByTitle(String title) {


        return blogRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Blog Bulunamadı.."));
    }

    public void addCommets(String title, String email, String commet) {
        Blog foundBlog = getBlogByTitle(title);

        User user=userService.getUserByEmail(email);

        BlogComment blogComment = new BlogComment(user, commet);

        foundBlog.getBlogCommentList().add(blogComment);

        blogRepository.addComment(title, foundBlog);

        foundBlog.getBlogCommentList().add(new BlogComment(user, commet));


    }

    public List<Blog> getBlogsFilterByStatus(BlogStatus blogStatus, String email) {
        UserService userService = new UserService(new UserRepository());

        User foundUser = userService.getUserByEmail(email);

        return foundUser.getBlogList().stream()
                .filter(blog -> blogStatus.equals(blog.getBlogStatus()))
                .toList();
    }


    public void changeBlogStatus(BlogStatus blogStatus, String title) {
        Blog foundBlog = getBlogByTitle(title);

        if (foundBlog.getBlogStatus().equals(BlogStatus.PUBLISHED)) {
            throw new RuntimeException("Statusu Publish Edilen Bir Blog Silinemez.");
        }


        //Log Eklenecek
        foundBlog.setBlogStatus(blogStatus);

    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void likeBlog(String title, String email) {
        Blog blog = getBlogByTitle(title);

        blog.setLikeCount(blog.getLikeCount() + 1);

        blogRepository.likeblog(title, blog);
    }

    public Long getLikeCountByTitle(String title) {
        Blog blog=getBlogByTitle(title);

        return blog.getLikeCount();
    }
}
