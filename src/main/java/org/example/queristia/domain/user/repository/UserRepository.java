package org.example.queristia.domain.user.repository;

import org.example.queristia.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
