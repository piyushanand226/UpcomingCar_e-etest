package com.renault.upcomingcar.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class UserServiceSecurityTest {
    @Autowired private com.renault.upcomingcar.domain.service.UserService userService;
    @Autowired private com.renault.upcomingcar.domain.repository.UserRepository userRepository;
    @Autowired private org.springframework.security.crypto.password.PasswordEncoder encoder;

    @BeforeEach
    void seed() {
        userRepository.deleteAll();
        var user = new com.renault.upcomingcar.domain.entity.User();
        user.setUsername("piyush"); user.setPassword(encoder.encode("plainPassword")); user.setUserRole("USER"); userRepository.save(user);
        var admin = new com.renault.upcomingcar.domain.entity.User();
        admin.setUsername("admin"); admin.setPassword(encoder.encode("adminPassword")); admin.setUserRole("ADMIN"); userRepository.save(admin);
    }

    @Test @WithMockUser(username="admin", roles={"ADMIN"})
    void adminCanDeleteUser() {
        var u = userRepository.findByUsername("piyush").orElseThrow();
        userService.deleteUserById(u.getUserId());
        org.assertj.core.api.Assertions.assertThat(userRepository.findById(u.getUserId())).isEmpty();
    }

    @Test @WithMockUser(username="user", roles={"USER"})
    void userCannotDeleteUser() {
        var u = userRepository.findByUsername("piyush").orElseThrow();
        org.junit.jupiter.api.Assertions.assertThrows(org.springframework.security.access.AccessDeniedException.class, () -> userService.deleteUserById(u.getUserId()));
    }
}