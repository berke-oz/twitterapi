package com.twitter.twitterapi.service;

import com.twitter.twitterapi.dto.CommentRequest;
import com.twitter.twitterapi.dto.CommentResponse;
import com.twitter.twitterapi.entity.Comment;
import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.exceptions.*;
import com.twitter.twitterapi.repository.CommentRepository;
import com.twitter.twitterapi.repository.TweetRepository;
import com.twitter.twitterapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;


    @Override
    public CommentResponse createComment(CommentRequest request, String userEmail) {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new ApiException("User not found !", HttpStatus.NOT_FOUND));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException(request.getTweetId()));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setTweet(tweet);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponse(savedComment.getId(), savedComment.getContent(), user.getUserName(),savedComment.getCreatedAt());
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest request, String userEmail) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        if(!comment.getUser().getEmail().equals(userEmail)){
            throw new UnauthorizedCommentUpdateException();
        }

        comment.setContent(request.getContent());
        Comment updatedComment = commentRepository.save(comment);

        return new CommentResponse(
                updatedComment.getId(),
                updatedComment.getContent(),
                updatedComment.getUser().getUserName(),
                updatedComment.getCreatedAt()
        );
    }


}
