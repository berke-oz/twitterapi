package com.twitter.twitterapi.service;

import com.twitter.twitterapi.dto.CommentRequest;
import com.twitter.twitterapi.dto.CommentResponse;

public interface CommentService {

    CommentResponse createComment(CommentRequest request, String userEmail);
}
