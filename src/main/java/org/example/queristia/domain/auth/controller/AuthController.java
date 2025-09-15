package org.example.queristia.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.queristia.domain.auth.dto.UserSaveRequest;
import org.example.queristia.domain.auth.dto.UserSaveResponse;
import org.example.queristia.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserSaveResponse> Signup(@RequestBody UserSaveRequest userSaveRequest) {

        return ResponseEntity.ok().body(authService.signup(userSaveRequest));
    }
}
