package com.twitter.twitterapi.service;

import com.twitter.twitterapi.entity.Like;
import com.twitter.twitterapi.entity.Tweet;
import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.exceptions.ApiException;
import com.twitter.twitterapi.exceptions.TweetNotFoundException;
import com.twitter.twitterapi.repository.LikeRepository;
import com.twitter.twitterapi.repository.TweetRepository;
import com.twitter.twitterapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService{



    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public void likeTweet(Long id, String userEmail) {

       User user  = userRepository.findUserByEmail(userEmail)
               .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

       Tweet tweet = tweetRepository.findById(id)
               .orElseThrow(()-> new TweetNotFoundException(id));


       boolean alreadyliked = likeRepository
               .findAll()
               .stream()
               .anyMatch(like -> like.getUser().equals(user) && like.getTweet().equals(tweet));

       if(alreadyliked){
           throw new ApiException("You already liked this tweet !", HttpStatus.BAD_REQUEST);
       }

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);
        likeRepository.save(like);

    }

    @Transactional
    @Override
    public void disslikeTweet(Long id, String userEmail) {


        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id));

        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        Like like = likeRepository.findByUserAndTweet(user, tweet)
                .orElseThrow(() -> new ApiException("Like not found", HttpStatus.NOT_FOUND));

        likeRepository.deleteTweet(like.getId());

    }
}
