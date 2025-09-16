package org.example.queristia.domain.auth.dto;

import lombok.Getter;

@Getter
public class UserSaveRequest {
    private String username;
    private String userEmail;
    private String password;
    private String nickname;
}
