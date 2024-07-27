package com.patika.bloghubservice.dto.response;

import com.patika.bloghubservice.model.BlogTag;
import com.patika.bloghubservice.model.SocialMedia;
import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.model.enums.UserType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private String bio;
    private Set<SocialMedia> socialMediaList;

}
