package com.twitter.twitterapi.exceptions;

import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends ApiException {

    public CommentNotFoundException(Long id) {
        super("Comment not found with id " + id, HttpStatus.NOT_FOUND);
    }
}
