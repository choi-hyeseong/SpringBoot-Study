package com.division.springbootstudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "MEMBER_ID")
    @ManyToOne()
    private Member member; //굳이 무거운 WebUser를 담을 필요가 있을까? 그래서 가벼운 member를 드리겠읍니다
    private String title;
    private String text;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FILE_ID")
    //joincolum 써줘야 하고, 단방향 일대다면 join column 써야함
    private List<FileEntity> files = new ArrayList<>();

    public void update(String title, String text) {
        this.title = title;
        this.text = text;
    }

}
