package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.UserRole;
import com.division.springbootstudy.domain.WebUser;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    private String name;
    private int age;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public WebUser toEntity() {
        return WebUser.builder()
                      .username(username)
                      .password(password)
                      .role(role)
                      .build();
    }

    public Member toMemberEntity() {
        return Member.builder()
                     .age(age)
                     .username(username)
                     .realName(name)
                     .email(email)
                     .build();
    }
}
