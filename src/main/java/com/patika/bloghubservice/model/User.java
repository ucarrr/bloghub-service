package com.patika.bloghubservice.model;

import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.model.enums.UserType;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class User {
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private StatusType statusType;
    private String bio;
    private Set<SocialMedia> socialMediaList;
    private List<Blog> blogList = new ArrayList<>();
    private List<BlogTag> followedTagList = new ArrayList<>();


    public User() {
        userType = UserType.STANDARD;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.statusType = StatusType.WAITING_APPROVAL;
        this.userType = UserType.STANDARD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<SocialMedia> getSocialMediaList() {
        return socialMediaList;
    }

    public void setSocialMediaList(Set<SocialMedia> socialMediaList) {
        this.socialMediaList = socialMediaList;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public List<BlogTag> getFollowedTagList() {
        return followedTagList;
    }

    public void setFollowedTagList(List<BlogTag> followedTagList) {
        this.followedTagList = followedTagList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", statusType=" + statusType +
                ", bio='" + bio + '\'' +
                ", socialMediaList=" + socialMediaList +
                ", blogList=" + blogList +
                ", followedTagList=" + followedTagList +
                '}';
    }
}
