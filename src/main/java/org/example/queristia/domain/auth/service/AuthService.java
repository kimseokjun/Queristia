package org.example.queristia.domain.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.queristia.config.Jwt.JwtUtil;
import org.example.queristia.config.PasswordEncoder;
import org.example.queristia.domain.auth.dto.request.UserSaveRequest;
import org.example.queristia.domain.auth.dto.request.UserSigninRequest;
import org.example.queristia.domain.auth.dto.response.UserSaveResponse;
import org.example.queristia.domain.auth.dto.response.UserSigninResponse;
import org.example.queristia.domain.user.UserRank;
import org.example.queristia.domain.user.entity.User;
import org.example.queristia.domain.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserSaveResponse signup(UserSaveRequest userSaveRequest) {

        String encodedPassword = passwordEncoder.encode(userSaveRequest.password());
        User user = User.of(
                userSaveRequest.username()
                , userSaveRequest.userEmail()
                , encodedPassword
                , userSaveRequest.nickname()
                , UserRank.Beginner
                , 0);

        System.out.println(user.getNickname());
        User usersaved = userRepository.save(user);

        return new UserSaveResponse(
                usersaved.getId(),
                usersaved.getUsername(),
                usersaved.getUserEmail(),
                usersaved.getNickname(),
                usersaved.getRank(),
                usersaved.getPopularity(),
                usersaved.getCreatedAt());

    }

    @Transactional
    public UserSigninResponse signin(UserSigninRequest userSigninRequest) {

        System.out.println("userSigninRequest = " + userSigninRequest);
        //유저 네임 검증
        User user = userRepository.findByUsername(userSigninRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));

        // 비번 일치 검증
        if (!passwordEncoder.matches(userSigninRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String accessToken = jwtUtil.createAccessToken(user);

        return new UserSigninResponse(
                user.getUsername(),
                accessToken
        );
    }
}
