package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Like;
import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndTweet(User user, Tweet tweet);
    @Modifying
    @Query(value = "DELETE FROM likes WHERE id = :id", nativeQuery = true)
    void deleteTweet(@Param("id") Long id);




}
