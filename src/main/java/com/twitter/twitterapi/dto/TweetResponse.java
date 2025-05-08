package com.twitter.twitterapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TweetResponse(Long id, String content, LocalDateTime dateTime, String userName, List<CommentResponse> comments, List<LikeResponse> likes) {
}
