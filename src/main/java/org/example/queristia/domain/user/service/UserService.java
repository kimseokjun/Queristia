package org.example.queristia.domain.user.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.queristia.domain.user.UserRank;
import org.example.queristia.domain.user.dto.UserSaveRequest;
import org.example.queristia.domain.user.dto.UserSaveResponse;
import org.example.queristia.domain.user.entity.User;
import org.example.queristia.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponse signup(UserSaveRequest userSaveRequest) {
        User user = User.of(userSaveRequest.getUserEmail()
                ,userSaveRequest.getPassword()
                ,userSaveRequest.getNickname()
                ,UserRank.Beginner
                ,0);

        System.out.println(user.getNickname());
        User usersaved = userRepository.save(user);

        return new UserSaveResponse(usersaved.getId(),usersaved.getUserEmail(),usersaved.getNickname(),usersaved.getRank(),usersaved.getPopularity(),usersaved.getCreatedAt());

    }
}
