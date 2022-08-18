package com.youngjo.ssg.domain.user.repository;

import com.youngjo.ssg.domain.user.domain.User;

public interface UserRepository {
    Long saveUser(User user);

    User findUserById(Long id);

    User findUserByLoginId(String userId);

    User findUserByEmail(String email);
}
