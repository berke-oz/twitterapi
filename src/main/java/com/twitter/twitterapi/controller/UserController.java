package com.twitter.twitterapi.controller;

import com.twitter.twitterapi.dto.UserRequest;
import com.twitter.twitterapi.dto.UserResponse;
import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.service.UserService;
import com.twitter.twitterapi.util.Convertor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final Convertor convertor;




    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return convertor.userResponseConvert(user);
    }


    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = convertor.toUser(userRequest);
        User updatedUser = userService.updateUser(id, user);

        return convertor.userResponseConvert(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping
    public List<UserResponse> getAllUser(){
        List<User> users = userService.getAllUsers();
        return convertor.toUserResponseList(users);
    }



}
