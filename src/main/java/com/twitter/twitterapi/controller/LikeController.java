package com.twitter.twitterapi.controller;

import com.twitter.twitterapi.dto.LikeResponse;
import com.twitter.twitterapi.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;


    @PostMapping("/like/{id}")

    public ResponseEntity<String> likeTweet(@PathVariable Long id, Authentication authentication){
        String currentUserEmail = authentication.getName();
        likeService.likeTweet(id,currentUserEmail);
        return ResponseEntity.ok("Tweet liked");
    }

    @PostMapping("/disslike/{id}")
    public ResponseEntity<String> disslikeTweet(@PathVariable Long id, Authentication authentication){
        String currentUserEmail = authentication.getName();
        likeService.disslikeTweet(id,currentUserEmail);
        return ResponseEntity.ok("Like removed from tweet.");
    }

}
