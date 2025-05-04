package com.twitter.twitterapi.controller;

import com.twitter.twitterapi.dto.LoginRequest;
import com.twitter.twitterapi.dto.UserRequest;
import com.twitter.twitterapi.dto.UserResponse;
import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.service.AuthService;
import com.twitter.twitterapi.service.UserService;
import com.twitter.twitterapi.util.Convertor;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Convertor convertor;

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest){
        User user = convertor.toUser(userRequest); //
        User savedUser = authService.register(user);
        return convertor.userResponseConvert(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        String response = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

}
