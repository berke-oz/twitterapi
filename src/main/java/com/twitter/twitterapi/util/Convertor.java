package com.twitter.twitterapi.util;


import com.twitter.twitterapi.dto.UserRequest;
import com.twitter.twitterapi.dto.UserResponse;
import com.twitter.twitterapi.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Convertor {

    public UserResponse userResponseConvert(User user){
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getTweets());

    }

    public User toUser(UserRequest request){
       User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());

        return user;

    }

    public List<UserResponse> toUserResponseList(List<User> users){

        List<UserResponse> userResponses = new ArrayList<>();

        for(User user: users){
            userResponses.add(userResponseConvert(user));

        }
        return userResponses;
    }

}
