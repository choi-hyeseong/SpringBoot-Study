package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.Reply;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long boardId;
    private String text;
    private Member member;


    public Reply toEntity() {
        return Reply.builder()
                    .text(text)
                .member(member)
                    .build();
    }
}
