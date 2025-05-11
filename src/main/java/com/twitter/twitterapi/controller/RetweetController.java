package com.twitter.twitterapi.controller;


import com.twitter.twitterapi.dto.RetweetRequest;
import com.twitter.twitterapi.dto.RetweetResponse;
import com.twitter.twitterapi.dto.TweetRequest;
import com.twitter.twitterapi.service.RetweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/retweets")
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<RetweetResponse> retweet(@RequestBody RetweetRequest retweetRequest, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        RetweetResponse retweetResponse = retweetService.retweet(retweetRequest.getTweetId(), currentUserEmail);
        return new ResponseEntity<>(retweetResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> undoRetweet(@PathVariable Long id, Authentication authentication){
        String currentUserEmail = authentication.getName();
        String retweetResponse = retweetService.undoRetweet(id, currentUserEmail);
        return new ResponseEntity<String>("Retweet undone!",HttpStatus.OK);
    }

}
