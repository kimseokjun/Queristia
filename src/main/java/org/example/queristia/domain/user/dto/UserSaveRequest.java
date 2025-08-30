package org.example.queristia.domain.user.dto;

import lombok.Getter;

@Getter
public class UserSaveRequest {

    private String userEmail;
    private String password;
    private String nickname;
}
