package com.twitter.twitterapi.service;

import com.twitter.twitterapi.entity.User;
import com.twitter.twitterapi.exceptions.UserException;
import com.twitter.twitterapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User is not found with id" + id, HttpStatus.NOT_FOUND));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User updatedUser = getUserById(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
       return userRepository.save(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
