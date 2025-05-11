    package com.twitter.twitterapi.service;

    import com.twitter.twitterapi.dto.RetweetResponse;
    import com.twitter.twitterapi.entity.Retweet;
    import com.twitter.twitterapi.entity.Tweet;
    import com.twitter.twitterapi.entity.User;
    import com.twitter.twitterapi.exceptions.ApiException;
    import com.twitter.twitterapi.exceptions.RetweetNotFoundException;
    import com.twitter.twitterapi.exceptions.TweetNotFoundException;
    import com.twitter.twitterapi.repository.RetweetRepository;
    import com.twitter.twitterapi.repository.TweetRepository;
    import com.twitter.twitterapi.repository.UserRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;

    @Service
    @AllArgsConstructor
    public class RetweetServiceImpl implements RetweetService {

        private final RetweetRepository retweetRepository;
        private final TweetRepository tweetRepository;
        private final UserRepository userRepository;

        @Override
        public RetweetResponse retweet(Long tweetId, String userEmail) {
            User user = userRepository.findUserByEmail(userEmail)
                    .orElseThrow(()-> new ApiException("User not found", HttpStatus.NOT_FOUND));

            Tweet tweet = tweetRepository.findById(tweetId)
                    .orElseThrow(()-> new TweetNotFoundException(tweetId));

            if (retweetRepository.existsByUserAndTweet(user, tweet)) {
                throw new ApiException("You have already retweeted this tweet", HttpStatus.BAD_REQUEST);
            }

            Retweet retweet = new Retweet();
            retweet.setUser(user);
            retweet.setTweet(tweet);

            Retweet savedRetweet = retweetRepository.save(retweet);


            return new RetweetResponse(
                    savedRetweet.getId(),
                    savedRetweet.getTweet().getId(),
                    savedRetweet.getUser().getUserName(),
                    savedRetweet.getTweet().getContent()
                    );

        }

        @Override
        public String undoRetweet(Long tweetId, String userEmail) {
            User user = userRepository.findUserByEmail(userEmail)
                    .orElseThrow(()-> new ApiException("User not found",HttpStatus.NOT_FOUND));

            Tweet tweet = tweetRepository.findById(tweetId)
                    .orElseThrow(()-> new TweetNotFoundException(tweetId));

            Retweet retweet = retweetRepository.findByUserAndTweet(user,tweet)
                    .orElseThrow(()-> new RetweetNotFoundException(tweetId));

            retweetRepository.deleteByRetweetId(tweetId);

            return "Retweet undone!";
        }
    }
