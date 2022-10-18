package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.WebUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardVO {

    private Long id;
    private UserResponseDto user;
    private String title;
    private String text;
    private LocalDateTime writtenDate;
    private List<FileResponseDto> file;
    private List<ReplyResponseDto> replies;

    public BoardVO(Board board) {
        this.id = board.getId();
        this.user = new UserResponseDto(board.getMember());
        this.title = board.getTitle();
        this.text = board.getText();
        this.writtenDate = board.getLocalDateTime();
        this.file = board.getFiles().stream().map(FileResponseDto::new).collect(Collectors.toList());
        this.replies = board.getReplies().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }


}
