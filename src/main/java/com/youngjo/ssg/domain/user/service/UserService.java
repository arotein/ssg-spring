package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;

public interface UserService {
    void signUp(SignUpReqDto dto);

    Boolean checkForLoginIdDuplicate(String loginId);

    void updateLastAccessTime(Long id);
}
