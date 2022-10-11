package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    private int age;

    public User toEntity() {
        return User.builder()
                .age(age)
                .name(name)
                .build();
    }

}
