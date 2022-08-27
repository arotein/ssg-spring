package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;

public interface UserService {
    Boolean signUp(SignUpReqDto dto);

    Boolean checkForLoginIdDuplicate(String loginId);

    Boolean checkForEmailDuplicates(String email);

    void updateLastAccessTime(Long id);
}