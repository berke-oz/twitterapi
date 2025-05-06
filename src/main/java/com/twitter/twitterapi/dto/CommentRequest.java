package com.twitter.twitterapi.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long tweetId;
}
