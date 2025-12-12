package com.renault.upcomingcar.integration;

import com.renault.upcomingcar.api.dto.AuthRequest;
import com.renault.upcomingcar.api.dto.AuthResponse;
import com.renault.upcomingcar.domain.entity.User;
import com.renault.upcomingcar.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtAuthenticationIntegrationTest {

    @Autowired private TestRestTemplate rest;
    @Autowired private UserRepository repo;
    @Autowired private org.springframework.security.crypto.password.PasswordEncoder encoder;

    @BeforeEach
    void seed() {
        repo.deleteAll();

        var user = new User();
        user.setUsername("piyush");
        user.setPassword(encoder.encode("plainPassword"));
        user.setRole("USER");
        repo.save(user);

        var admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("adminPassword"));
        admin.setRole("ADMIN");
        repo.save(admin);
    }

    private String auth(String u, String p) {
        var req = new AuthRequest(u, p);
        var res = rest.postForEntity("/authenticate", req, AuthResponse.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
        return res.getBody().getToken(); // record accessor
    }

    @Test
    void userAccessCars() {
        String token = auth("piyush", "plainPassword");
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);

        var res = rest.exchange("/cars", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    void userForbiddenAdminCars() {
        String token = auth("piyush", "plainPassword");
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);

        var res = rest.exchange("/admin/cars", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
    }

    @Test
    void adminAccessUsers() {
        String token = auth("admin", "adminPassword");
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);

        var res = rest.exchange("/users", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}