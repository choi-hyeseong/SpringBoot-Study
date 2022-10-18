package com.division.springbootstudy.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileEntity {
    //객체 매핑에서 연관관계의 주인은 N쪽
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String modifiedName;

    //N:1 매핑, 외래키를 주인에서 관리
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
