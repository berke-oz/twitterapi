package com.twitter.twitterapi.dto;

import com.twitter.twitterapi.entity.Tweet;

import java.util.List;

public record UserResponse(Long id, String firstName, String lastName, String email, List<Tweet> tweets) {
}
