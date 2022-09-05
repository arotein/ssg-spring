package com.youngjo.ssg.domain.user.service;

import com.youngjo.ssg.domain.user.dto.request.SignUpReqDto;
import com.youngjo.ssg.domain.user.dto.response.MyPageInfoResDto;

public interface UserService {
    Boolean signUp(SignUpReqDto dto);

    Boolean checkForLoginIdDuplicate(String loginId);

    Boolean checkForEmailDuplicates(String email);

    MyPageInfoResDto myPageInfo();

    void updateLastAccessTime(Long id);
}