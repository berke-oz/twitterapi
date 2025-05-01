package com.twitter.twitterapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UserException extends RuntimeException {
    private HttpStatus httpStatus;

    public UserException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

}
