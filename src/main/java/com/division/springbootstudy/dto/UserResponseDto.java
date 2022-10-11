package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    //HTTP response entity, 구조는 이렇게.

    private Long id;
    private String name;
    private int age;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

}
