package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
