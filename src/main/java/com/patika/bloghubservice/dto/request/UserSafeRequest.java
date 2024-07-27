package com.patika.bloghubservice.dto.request;

import com.patika.bloghubservice.model.BlogTag;
import com.patika.bloghubservice.model.SocialMedia;
import com.patika.bloghubservice.model.enums.Blog;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSafeRequest {

    private String name;
    private String email;
    private String password;

}
