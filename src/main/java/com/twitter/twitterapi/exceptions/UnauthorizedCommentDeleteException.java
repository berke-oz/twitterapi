package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedCommentDeleteException extends ApiException {
    public UnauthorizedCommentDeleteException() {
        super("You are not authorized to delete this comment", HttpStatus.BAD_REQUEST);
    }
}
