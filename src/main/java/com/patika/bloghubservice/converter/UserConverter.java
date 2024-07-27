package com.patika.bloghubservice.converter;

import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {


    /*
    private UserConverter(){

    }
    */
    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .bio(user.getBio())
                .userType(user.getUserType())
                .build();
    }

    public static List<UserResponse> toResponse(List<User> users) {
        return  users.stream().map(UserConverter::toResponse).toList();
    }
}
