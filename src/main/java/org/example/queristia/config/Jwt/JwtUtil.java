package org.example.queristia.config.Jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.example.queristia.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;

    @PostConstruct
    public void init() {
        // HS256 → 최소 32바이트 이상의 key 필요
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 액세스 토큰 생성
    public String createAccessToken(User user) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId())) // 기본 subject는 userId
                .claim("username", user.getUsername())
                .claim("nickname", user.getNickname())
                .claim("rank", user.getRank().name())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JwtConsts.ACCESS_TOKEN_EXPIRATION))
                .signWith(key, signatureAlgorithm)
                .compact();
    }


    // JWT 검증
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
