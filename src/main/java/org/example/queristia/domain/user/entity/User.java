package org.example.queristia.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.queristia.domain.user.UserRank;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRank")
    private UserRank rank;

    private long popularity = 0;

    private LocalDateTime createdAt;

    private User(String username, String userEmail, String password, String nickname, UserRank rank, long popularity, LocalDateTime createdAt) {
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
        this.nickname = nickname;
        this.rank = rank;
        this.popularity = popularity;
        this.createdAt = createdAt;
    }

    public static User of(
            String username,
            String userEmail,
            String password,
            String nickname,
            UserRank rank,
            long popularity) {
        return new User(
                username,
                userEmail,
                password,
                nickname,
                rank,
                popularity,
                LocalDateTime.now()
        );
    }


}
