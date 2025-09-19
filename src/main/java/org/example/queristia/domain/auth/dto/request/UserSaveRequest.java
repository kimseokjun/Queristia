package org.example.queristia.domain.auth.dto.request;

public record UserSaveRequest(String username, String userEmail, String password, String nickname) {
}
