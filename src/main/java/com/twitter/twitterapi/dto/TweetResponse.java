package com.twitter.twitterapi.dto;

import java.time.LocalDateTime;

public record TweetResponse(Long id, String content, LocalDateTime dateTime, String userName) {
}
