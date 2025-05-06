package com.twitter.twitterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public record CommentResponse (Long id, String content, String userName, Long tweetId, LocalDateTime createdAt){
}
