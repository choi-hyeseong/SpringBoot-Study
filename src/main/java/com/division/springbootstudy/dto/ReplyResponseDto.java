package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private Long id;
    private String username;
    private String text;

    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.text = reply.getText();
        this.username = reply.getMember().getUsername();
    }
}
