package com.youngjo.ssg.global.security.dto;

import com.youngjo.ssg.global.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
