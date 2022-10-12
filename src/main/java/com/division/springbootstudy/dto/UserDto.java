package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.UserRole;
import com.division.springbootstudy.domain.WebUser;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    private int age;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public WebUser toEntity() {
        return WebUser.builder()
                      .age(age)
                      .name(name)
                      .email(email)
                      .username(username)
                      .password(password)
                      .build();
    }

}
