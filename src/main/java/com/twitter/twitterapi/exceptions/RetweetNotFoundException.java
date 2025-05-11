package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class RetweetNotFoundException extends ApiException {
    public RetweetNotFoundException(Long tweetId) {
        super("Retweet not found for the tweet" + tweetId, HttpStatus.NOT_FOUND);
    }
}
