package com.twitter.twitterapi.service;

import com.twitter.twitterapi.dto.CommentResponse;
import com.twitter.twitterapi.dto.TweetRequest;
import com.twitter.twitterapi.dto.TweetResponse;
import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.exceptions.TweetNotFoundException;
import com.twitter.twitterapi.exceptions.UnauthorizedTweetDeleteException;
import com.twitter.twitterapi.exceptions.UnauthorizedTweetUpdateException;
import com.twitter.twitterapi.repository.TweetRepository;
import com.twitter.twitterapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public TweetResponse createTweet(TweetRequest request, String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found !"));

        Tweet tweet = new Tweet();
        tweet.setContent(request.getContent());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUser(user);

        Tweet savedTweet = tweetRepository.save(tweet);
        return new TweetResponse(
                savedTweet.getId(),
                savedTweet.getContent(),
                savedTweet.getCreatedAt(),
                user.getUserName(),
                List.of()

        );
    }

    

    @Override
    public List<TweetResponse> getTweetsByUserId(Long userId) {
        List<Tweet> tweets = tweetRepository.findTweetByUserId(userId);

        return tweets.stream()
                .map(tweet -> new TweetResponse(tweet.getId(), tweet.getContent(), tweet.getCreatedAt(), tweet.getUser().getUserName(),List.of()))
                .collect(Collectors.toList());

    }

    @Override
    public TweetResponse getTweetById(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id));

        List<CommentResponse> commentResponses = tweet.getComments().stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUserName(),
                        comment.getCreatedAt()
                ))
                .collect(Collectors.toList());
        return new TweetResponse(
                tweet.getId(),
                tweet.getContent(),
                tweet.getCreatedAt(),
                tweet.getUser().getUserName(),
                commentResponses
        );
    }

    @Override
        public TweetResponse updateTweet(Long id, TweetRequest tweetRequest, String userEmail) {
            Tweet tweet = tweetRepository.findById(id)
                    .orElseThrow(() -> new TweetNotFoundException(id));
            if(!tweet.getUser().getEmail().equals(userEmail)){
                throw new UnauthorizedTweetUpdateException();
            }

            tweet.setContent(tweetRequest.getContent());
            Tweet uptadedTweet = tweetRepository.save(tweet);

            List<CommentResponse> commentResponses = uptadedTweet.getComments().stream()
                    .map(comment -> new CommentResponse(
                            comment.getId(),
                            comment.getContent(),
                            comment.getUser().getUserName(),
                            comment.getCreatedAt()
                    )).collect(Collectors.toList());

            return new TweetResponse(
                    uptadedTweet.getId(),
                    uptadedTweet.getContent(),
                    uptadedTweet.getCreatedAt(),
                    uptadedTweet.getUser().getUserName(),
                    commentResponses
            );
        }

    @Transactional
    @Override
    public void deleteTweet(Long id, String userEmail) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(()-> new TweetNotFoundException(id));

        if(!tweet.getUser().getEmail().equals(userEmail)){
            throw new UnauthorizedTweetDeleteException();
        }

        tweetRepository.delete(tweet);
    }
}
