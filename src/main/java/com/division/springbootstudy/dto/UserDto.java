package com.division.springbootstudy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String name;
    private int age;

    @Builder
    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
