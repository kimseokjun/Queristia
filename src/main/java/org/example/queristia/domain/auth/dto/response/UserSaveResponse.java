package org.example.queristia.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.queristia.domain.user.UserRank;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserSaveResponse {
    private long id;
    private String username;
    private String userEmail;
    private String nickname;
    private UserRank rank;
    private long popularity;
    private LocalDateTime createdAt;
}
