package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query(value = "DELETE FROM comments WHERE id = :id", nativeQuery = true)
    void hardDeleteById(@Param("id") Long id);



}
