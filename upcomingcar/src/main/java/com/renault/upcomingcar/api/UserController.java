package com.renault.upcomingcar.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.renault.upcomingcar.api.dto.CreateUserDto;
import com.renault.upcomingcar.domain.entity.User;
import com.renault.upcomingcar.domain.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<User> list() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User create(@RequestBody @Validated CreateUserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public java.util.Optional<User> get(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUserById(id.longValue());
    }
}