package com.renault.upcomingcar.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.renault.upcomingcar.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}