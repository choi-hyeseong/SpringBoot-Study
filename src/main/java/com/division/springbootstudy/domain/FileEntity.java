package com.division.springbootstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FileEntity {
    //객체 매핑에서 연관관계의 주인은 N쪽
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String modifiedName;

    /*@ManyToOne
    private Board board;
    단방향으로 짜도 될듯
    */

}
