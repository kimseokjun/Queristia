package org.example.queristia.config.Jwt;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import static org.example.queristia.config.Jwt.JwtConsts.ACCESS_COOKIE;
import static org.example.queristia.config.Jwt.JwtConsts.COOKIE_MAX_AGE;

@Component
public class CookieUtil {

    public ResponseCookie createAccessTokenCookie(String token) {
        return ResponseCookie.from(ACCESS_COOKIE, token)
                .httpOnly(true)
                .secure(true)  // 개발환경용
                .sameSite("Strict")
                .path("/")
                .maxAge(COOKIE_MAX_AGE)
                .build();
    }

    // 로그아웃시 쿠키 삭제용
    public ResponseCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(ACCESS_COOKIE, "")
                .httpOnly(true)
                .secure(true)  // 개발환경용
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
    }
}
