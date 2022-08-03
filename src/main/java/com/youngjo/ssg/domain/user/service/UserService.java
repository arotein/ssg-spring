package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.domain.User;
import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;

import java.util.List;

public interface UserService { // service에서는 dto를 받고 리턴함.
    void signUp(SignUpReqDto dto);

    void updateLastAccessTime(Long id);

    List<User> findAllUser();
}
