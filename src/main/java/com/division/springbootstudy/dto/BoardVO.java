package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.WebUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardVO {

    private Long id;
    private UserResponseDto user;
    private String title;
    private String text;
    private LocalDateTime writtenDate;

    public BoardVO(Board board) {
        this.id = board.getId();
        this.user = new UserResponseDto(board.getMember());
        this.title = board.getTitle();
        this.text = board.getText();
        this.writtenDate = board.getLocalDateTime();
    }


}
