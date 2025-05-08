package com.twitter.twitterapi.service;

public interface LikeService {

    void likeTweet(Long id, String userEmail);
    void disslikeTweet(Long id, String userEmail);
}
