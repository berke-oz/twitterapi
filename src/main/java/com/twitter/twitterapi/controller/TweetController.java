package com.twitter.twitterapi.controller;

import com.twitter.twitterapi.dto.TweetRequest;
import com.twitter.twitterapi.dto.TweetResponse;
import com.twitter.twitterapi.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;


    @PostMapping()
    public ResponseEntity<TweetResponse> createTweet(@RequestBody TweetRequest tweetRequest, Authentication authentication){
        String currentUserEmail = authentication.getName();
        TweetResponse tweetResponse = tweetService.createTweet(tweetRequest, currentUserEmail);
        return new ResponseEntity<>(tweetResponse, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetResponse>> getTweetsByUserId(@PathVariable Long userId){
        List<TweetResponse> tweets = tweetService.getTweetsByUserId(userId);
        return new ResponseEntity<>(tweets, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TweetResponse> getTweetById(@PathVariable Long id){
        TweetResponse tweet = tweetService.getTweetById(id);
        return new ResponseEntity<>(tweet, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TweetResponse> updateTweetById(@PathVariable Long id, @RequestBody TweetRequest tweetRequest, Authentication authentication ){
        String currentUserEmail = authentication.getName();
        TweetResponse updatedTweet = tweetService.updateTweet(id, tweetRequest, currentUserEmail);

        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, Authentication authentication) {

        String userEmail = authentication.getName();

        tweetService.deleteTweet(id, userEmail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
