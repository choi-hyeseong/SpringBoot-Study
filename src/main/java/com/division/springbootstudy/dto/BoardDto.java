package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.FileEntity;
import com.division.springbootstudy.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {

    private Member member;
    private String title;
    private String text;
    private List<FileDto> fileList;

    public Board toEntity() {
        return Board.builder()
                    .title(title)
                    .text(text)
                .member(member)
                .files(fileList.stream().map(FileDto::toEntity).collect(Collectors.toList()))
                    .build();
    }
}
