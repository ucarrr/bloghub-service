package com.patika.bloghubservice.service;


import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class  UserService {
    private UserRepository userRepository;
    private String spliter = ",";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {


        User savedUser = new User(user.getEmail(), user.getPassword());

        System.out.println("User saved: " + user.getEmail() + spliter + user.getPassword());

        userRepository.save(savedUser);

        return savedUser;

    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Bulunamadı."));
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void changeStatus(String email, StatusType statusType) {

        User foundUser = getUserByEmail(email);
        foundUser.setStatusType(statusType);

        userRepository.changeStatus(email, foundUser);
        //getUserByEmail(email).setStatusType(statusType);
    }

    public void changeStatus(List<String> emailList, StatusType statusType) {
        Map<String, User> allUsersMap = getAllUsersMap();

        emailList.forEach(email -> {
            User user = allUsersMap.get(email);
            user.setStatusType(statusType);
        });

    }

    public void changeStatusBulk(List<String> emailList, StatusType statusType) {
        //Map<String, User> allUsersMap = getAllUsersMap();

        emailList.forEach(email -> changeStatus(email, statusType));

    }

    public Map<String, User> getAllUsersMap() {
        return getAllUsers().stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));

    }
}
