package com.twitter.twitterapi.repository;

import com.twitter.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
