package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedCommentUpdateException extends ApiException {

    public UnauthorizedCommentUpdateException() {
        super("You are not authorized to update this comment", HttpStatus.BAD_REQUEST);
    }
}
