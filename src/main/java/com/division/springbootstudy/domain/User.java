package com.division.springbootstudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
@Entity //엔티티객체 = db에서 바로 매핑
@NoArgsConstructor //생성자 없음
@Getter*/
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1씩 증가
    private Long id;

    private String name;

    private Integer age;

}
