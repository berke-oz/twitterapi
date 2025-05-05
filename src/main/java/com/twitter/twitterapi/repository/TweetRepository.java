package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findTweetByUserId(Long userId);
}
