package com.youngjo.ssg.domain.user.repository;

import com.youngjo.ssg.domain.user.domain.User;

import java.util.List;

public interface UserRepository {
    Long saveUser(User user);

    User findUserById(Long id);

    User findUserByLoginId(String userId);

    User findUserByEmail(String email);

    List<User> findAllUser();
}
