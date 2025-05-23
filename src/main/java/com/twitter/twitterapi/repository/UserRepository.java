package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
