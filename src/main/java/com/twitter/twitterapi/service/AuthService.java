package com.twitter.twitterapi.service;

import com.twitter.twitterapi.entity.User;

public interface AuthService {

    String login(String email, String password);
    User register(User user);
}
