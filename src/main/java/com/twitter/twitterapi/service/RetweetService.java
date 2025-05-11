package com.twitter.twitterapi.service;


import com.twitter.twitterapi.dto.RetweetResponse;
import com.twitter.twitterapi.entity.Retweet;
import org.springframework.stereotype.Service;



public interface RetweetService {
    RetweetResponse retweet(Long tweetId, String userEmail);
    String undoRetweet(Long tweetId, String userEmail);



}
