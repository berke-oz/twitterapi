    package com.twitter.twitterapi.service;

    import com.twitter.twitterapi.entity.User;
    import com.twitter.twitterapi.exceptions.ApiException;
    import com.twitter.twitterapi.repository.UserRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    @AllArgsConstructor
    @Service
    public class AuthServiceImpl implements AuthService{


        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;


        @Override
        public String login(String email, String password) {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new ApiException("User not found with email: " + email, HttpStatus.NOT_FOUND));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new ApiException("Invalid password", HttpStatus.UNAUTHORIZED);
            }

            return "Login successful";
        }


            @Override
            public User register(User user) {
                if (userRepository.existsByUserName(user.getUserName())) {
                    throw new ApiException("Username already exists", HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByEmail(user.getEmail())) {
                    throw new ApiException("Email already registered", HttpStatus.BAD_REQUEST);
                }
                user.setPassword(passwordEncoder.encode(user.getPassword()));

                return userRepository.save(user);
            }
    }
