package com.renault.upcomingcar.domain.service;

import com.renault.upcomingcar.domain.entity.User;

public interface UserService {
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    User saveUser(User user);

    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    java.util.List<User> findAllUsers();

    @org.springframework.security.access.prepost.PreAuthorize("hasAnyRole('USER','ADMIN')")
    java.util.Optional<User> findUserById(Integer id);

    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    void deleteUserById(Long long1);




}