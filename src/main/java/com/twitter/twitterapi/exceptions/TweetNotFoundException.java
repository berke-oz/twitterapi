package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class TweetNotFoundException extends ApiException{
    public TweetNotFoundException(Long id) {
        super("Tweet not found with id " + id, HttpStatus.NOT_FOUND);
    }
}
