package com.division.springbootstudy.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //작성자

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board; //작성된 게시판
}
