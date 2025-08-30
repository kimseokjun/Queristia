package org.example.queristia.domain.user.dto;

import org.example.queristia.domain.user.UserRank;

import java.time.LocalDateTime;

public class UserSaveResponse {
    private long id;
    private String userEmail;
    private String nickname;
    private UserRank rank;
    private long popularity;
    private LocalDateTime createdAt;
}
