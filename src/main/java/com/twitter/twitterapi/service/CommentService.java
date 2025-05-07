package com.twitter.twitterapi.service;

import com.twitter.twitterapi.dto.CommentRequest;
import com.twitter.twitterapi.dto.CommentResponse;
import com.twitter.twitterapi.entity.Comment;

public interface CommentService {

    CommentResponse createComment(CommentRequest request, String userEmail);
    CommentResponse updateComment(Long id, CommentRequest request, String userEmail);
    
}
