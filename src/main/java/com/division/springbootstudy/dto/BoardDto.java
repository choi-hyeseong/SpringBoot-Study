package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.WebUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {

    private Member member;
    private String title;
    private String text;

    public Board toEntity() {
        return Board.builder()
                    .title(title)
                    .text(text)
                .member(member)
                    .build();
    }
}
