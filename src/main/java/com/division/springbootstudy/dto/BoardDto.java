package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.FileEntity;
import com.division.springbootstudy.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                    .files(new ArrayList<>())
                .replies(new ArrayList<>())
                    .member(member)
                    .build();
    }
}
