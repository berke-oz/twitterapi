package com.twitter.twitterapi.service;

import com.twitter.twitterapi.dto.TweetRequest;
import com.twitter.twitterapi.dto.TweetResponse;
import com.twitter.twitterapi.entity.Tweet;

import java.util.List;

public interface TweetService {

    TweetResponse createTweet(TweetRequest request, String userEmail);
    List<TweetResponse> getTweetsByUserId(Long userId);
    TweetResponse getTweetById(Long id);
    TweetResponse updateTweet(Long id, TweetRequest tweetRequest, String userEmail);
    void deleteTweet(Long id, String userEmail);



}
