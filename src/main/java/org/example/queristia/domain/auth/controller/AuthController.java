package org.example.queristia.domain.auth.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.queristia.config.Jwt.CookieUtil;
import org.example.queristia.domain.auth.dto.request.UserSaveRequest;
import org.example.queristia.domain.auth.dto.request.UserSigninRequest;
import org.example.queristia.domain.auth.dto.response.UserSaveResponse;
import org.example.queristia.domain.auth.dto.response.UserSigninResponse;
import org.example.queristia.domain.auth.service.AuthService;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final CookieUtil cookieUtil;

    @PostMapping("/api/v1/signup")
    public ResponseEntity<UserSaveResponse> Signup(@RequestBody UserSaveRequest userSaveRequest) {

        log.info("회원가입 요청 들어옴");
        log.info("Signup Request: {} ", userSaveRequest);

        return ResponseEntity.ok().body(authService.signup(userSaveRequest));
    }

    @PostMapping("/api/v1/signin")
    public ResponseEntity<UserSigninResponse> Signin(@RequestBody UserSigninRequest userSigninRequest, HttpServletResponse response) {

        log.info("로그인 요청 들어옴");
        log.info("Signup Request: {} ", userSigninRequest);

        UserSigninResponse userSigninResponse = authService.signin(userSigninRequest);

        ResponseCookie accessTokenCookie = cookieUtil.createAccessTokenCookie(userSigninResponse.getToken());
        response.addHeader("Set-Cookie", accessTokenCookie.toString());

        return ResponseEntity.ok().body(userSigninResponse);
    }

    // 로그아웃 메서드도 추가
    @PostMapping("/api/v1/signout")
    public ResponseEntity<String> signout(HttpServletResponse response) {

        log.info("로그아웃 요청 들어옴");
        ResponseCookie cookie = cookieUtil.deleteAccessTokenCookie();
        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok("Logged out successfully");
    }
}
