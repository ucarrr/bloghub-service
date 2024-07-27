package com.patika.bloghubservice.repository;



import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.StatusType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    //private List<User> userList = new ArrayList<>();
    private Map<String,User> userList = new HashMap<>();

    public void save(User user) {
        //userList.add(user);
        userList.put(user.getEmail(),user);
    }

    public Optional<User> findByEmail(String email) {
        /*return userList.stream()
                .filter(user -> user.getEmail()
                        .equals(email)).findFirst();*/
        return userList
                .values()
                .stream()
                .filter(user -> user.getEmail()
                        .equals(email)).findFirst();
    }

    public List<User> findAll() {
        return userList
                .values()
                .stream()
                .filter(user->user.getStatusType().equals(StatusType.APPROVED))
                .toList();
                //.collect(Collectors.toSet());

    }

    public void changeStatus(String email, User user) {
        userList.remove(email);
        userList.put(email, user);
    }
}
