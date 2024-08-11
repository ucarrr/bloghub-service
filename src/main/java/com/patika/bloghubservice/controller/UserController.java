package com.patika.bloghubservice.controller;

import com.patika.bloghubservice.dto.request.UserSafeRequest;
import com.patika.bloghubservice.dto.response.GenericResponse;
import com.patika.bloghubservice.dto.response.UserResponse;
import com.patika.bloghubservice.model.User;
import com.patika.bloghubservice.model.enums.StatusType;
import com.patika.bloghubservice.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public GenericResponse<UserResponse> createUser(@RequestBody UserSafeRequest request) {
        //return userService.saveUser(request);
        //return new ResponseEntity<>(userService.saveUser(request), HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
        //return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
        return GenericResponse.success(userService.saveUser(request), HttpStatus.CREATED);
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public  UserResponse  createUser(@RequestBody UserSafeRequest request) {
//        //return userService.saveUser(request);
//        //return new ResponseEntity<>(userService.saveUser(request), HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
//      return userService.saveUser(request);
//    }

    //    @GetMapping("/{email}")
//    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
//        //return userService.getUserByEmail(email);
//        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
//    }
    @GetMapping("/{email}")
    public GenericResponse<UserResponse> getUserByEmail(@PathVariable String email) {

        UserResponse response = userService.getUserByEmail(email);

//        GenericResponse successResponse = GenericResponse.builder()
//                .httpStatus(HttpStatus.OK)
//                .message("User found")
//                .data(response)
//                .build();


        return GenericResponse.success(response, HttpStatus.OK);
    }

    //Eskisi
    //    @GetMapping("/all")
//    public ResponseEntity<GenericResponse> getAllUsers() {
//        List<UserResponse> users = userService.getAllUsers();
//        return new ResponseEntity<>(GenericResponse.success(users, HttpStatus.OK), HttpStatus.OK);
//    }
    @GetMapping("/all")
    public GenericResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return GenericResponse.success(users, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public void changeStatus(@PathVariable String email, @PathParam("status") StatusType status) {
        userService.changeStatus(email, status);
    }

    @PutMapping()
    public void changeStatus() {
        //userService.changeStatusBulk(email, status);
    }
}