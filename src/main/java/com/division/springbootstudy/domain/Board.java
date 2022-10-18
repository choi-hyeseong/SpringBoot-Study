package com.division.springbootstudy.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascade all 지정되서 지워지면 따라서 지워짐
    //단방향 일대다면 join column 써야함 N:1인경우 mappedBy
    private List<FileEntity> files = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  //cascade all 지정되서 지워지면 따라서 지워짐
    private List<Reply> replies = new ArrayList<>();

    public void update(String title, String text) {
        this.title = title;
        this.text = text;
    }

    //양방향 매핑
    public void addFile(FileEntity entity) {
        Board exist = entity.getBoard();
        if (exist != null)
            exist.files.remove(entity);
        files.add(entity);
        entity.setBoard(this);
    }

    public void addReply(Reply reply) {
        Board exist = reply.getBoard();
        if (exist != null) {
            exist.replies.remove(reply);
        }
        replies.add(reply);
        reply.setBoard(this);
    }

    public void removeFile(FileEntity entity) {
        entity.setBoard(null);
        files.remove(entity);
    }

    public void removeReply(Reply reply) {
        reply.setBoard(null);
        replies.remove(reply);
    }

}
