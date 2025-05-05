package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedTweetUpdateException extends ApiException {
    public UnauthorizedTweetUpdateException() {
        super("You are not authorized to update this tweet.", HttpStatus.BAD_REQUEST);
    }
}
