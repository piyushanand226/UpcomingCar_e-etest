package com.renault.upcomingcar.service;

import com.renault.upcomingcar.domain.entity.User;
import com.renault.upcomingcar.domain.repository.UserRepository;
import com.renault.upcomingcar.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")   // ✅ ensures H2 + test profile is used
class UserServiceSecurityTest {

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder encoder;

    @BeforeEach
    void seed() {
        userRepository.deleteAll();

        var user = new User();
        user.setUsername("piyush");
        user.setPassword(encoder.encode("plainPassword"));
        user.setUserRole("USER");
        userRepository.save(user);

        var admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("adminPassword"));
        admin.setUserRole("ADMIN");
        userRepository.save(admin);
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void adminCanDeleteUser() {
        var u = userRepository.findByUsername("piyush").orElseThrow();
        userService.deleteUserById(u.getUserId());
        assertThat(userRepository.findById(u.getUserId())).isEmpty();
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void userCannotDeleteUser() {
        var u = userRepository.findByUsername("piyush").orElseThrow();
        assertThrows(AccessDeniedException.class,
            () -> userService.deleteUserById(u.getUserId()));
    }
}