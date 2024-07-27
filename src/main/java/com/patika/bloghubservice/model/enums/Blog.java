package com.patika.bloghubservice.model.enums;

import com.patika.bloghubservice.model.BlogComment;
import com.patika.bloghubservice.model.User;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Blog {
    private String title;
    private String text;
    private LocalDate date;
    private User user;
    private BlogStatus blogStatus;
    private Long likeCount;
    private List<BlogComment> blogCommentList = new ArrayList<>();


    public Blog(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.date = LocalDate.now();
        this.user = user;
        this.blogStatus = BlogStatus.DRAFT;
        this.likeCount=0L;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BlogStatus getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(BlogStatus blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public List<BlogComment> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(List<BlogComment> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", blogStatus=" + blogStatus +
                '}';
    }
}
