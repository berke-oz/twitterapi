package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Retweet;
import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByUserAndTweet(User user, Tweet tweet);
    boolean existsByUserAndTweet(User user, Tweet tweet);
    @Modifying
    @Transactional
    @Query("DELETE FROM Retweet r WHERE r.id = :retweetId")
    void deleteByRetweetId(Long retweetId);

}
