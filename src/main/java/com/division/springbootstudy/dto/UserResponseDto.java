package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.UserRole;
import com.division.springbootstudy.domain.WebUser;
import lombok.Getter;

@Getter
public class UserResponseDto {
    //HTTP response entity, 구조는 이렇게.

    private Long id;
    private String name;
    private int age;
    private String username;
    private String email;

    public UserResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getRealName();
        this.age = member.getAge();
        this.username = member.getUsername();
        this.email = member.getEmail();
    }

}
