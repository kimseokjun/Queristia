package org.example.queristia.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.queristia.domain.user.UserRank;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSaveResponse {
    private long id;
    private String userEmail;
    private String nickname;
    private UserRank rank;
    private long popularity;
    private LocalDateTime createdAt;
}
