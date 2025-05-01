package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
