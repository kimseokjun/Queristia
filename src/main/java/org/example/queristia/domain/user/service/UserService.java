package org.example.queristia.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.example.queristia.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
