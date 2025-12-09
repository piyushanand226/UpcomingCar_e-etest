package com.renault.upcomingcar.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.renault.upcomingcar.domain.entity.User;
import com.renault.upcomingcar.domain.repository.UserRepository;
import com.renault.upcomingcar.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public User saveUser(User user) {
        // Always encode password before saving
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepo.deleteById(id);
    }
}