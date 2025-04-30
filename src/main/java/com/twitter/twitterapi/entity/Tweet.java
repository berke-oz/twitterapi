package com.twitter.twitterapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tweets", schema = "public")
public class Tweet  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    @NotNull
    @NotBlank
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //N-1 = Tweet- User ***** 1-N Tweet - likes,comments,retweets

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Like> likes;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Retweet> retweets;
}
