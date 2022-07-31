package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;

import java.util.List;

public interface UserService {
    void signUp(SignUpReqDto dto);

    void updateLastAccessTime(Long id);

    List<User> findAllUser();
}
