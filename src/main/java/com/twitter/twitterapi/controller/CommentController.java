package com.twitter.twitterapi.controller;

import com.twitter.twitterapi.dto.CommentRequest;
import com.twitter.twitterapi.dto.CommentResponse;
import com.twitter.twitterapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<CommentResponse> createTweet(@RequestBody CommentRequest request, Authentication authentication){
        String userEmail = authentication.getName();
        CommentResponse comment = commentService.createComment(request, userEmail);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

}
