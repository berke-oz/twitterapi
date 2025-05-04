package com.twitter.twitterapi.dto;

import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;


}
