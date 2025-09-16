package org.example.queristia.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSigninResponse {

    String username;
    String token;

    @Override
    public String toString() {
        return "UserSigninResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
