package com.patika.bloghubservice.repository;

import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.model.enums.BlogStatus;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class BlogRepository {
    private Map<String, Blog> blogMap = new HashMap<>();

    public void save(Blog blog) {
        blogMap.put(blog.getTitle(),blog);
    }

    public Optional<Blog> findByTitle(String title) {
        return blogMap
                .values()
                .stream()
                .filter(blog -> blog.getTitle().equals(title))
                .filter(blog -> !blog.getBlogStatus().equals(BlogStatus.DELETED))
                .findFirst();
    }

    public List<Blog> findAll() {
        return blogMap.values().stream().toList();
    }

    public void addComment(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);
    }

    public void likeblog(String title, Blog blog) {
        blogMap.remove(title);
        blogMap.put(title, blog);

    }
}
