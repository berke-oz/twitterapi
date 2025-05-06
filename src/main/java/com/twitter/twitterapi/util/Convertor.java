package com.twitter.twitterapi.util;


import com.twitter.twitterapi.dto.TweetResponse;
import com.twitter.twitterapi.dto.UserRequest;
import com.twitter.twitterapi.dto.UserResponse;
import com.twitter.twitterapi.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Convertor {

    public UserResponse userResponseConvert(User user){
       List<TweetResponse> tweetResponses = user.getTweets().stream()
               .map(tweet -> new TweetResponse(
                       tweet.getId(),
                       tweet.getContent(),
                       tweet.getCreatedAt(),
                       tweet.getUser().getUserName()
               ))
               .collect(Collectors.toList());

       return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), tweetResponses);

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
