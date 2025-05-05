package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedTweetDeleteException extends ApiException {
    public UnauthorizedTweetDeleteException() {
        super("You are not authorized to delete this tweet", HttpStatus.BAD_REQUEST);
    }
}
