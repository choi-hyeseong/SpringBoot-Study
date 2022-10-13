package com.division.springbootstudy.repository;

import com.division.springbootstudy.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<WebUser, Long> {
    //repo 자동 생성 -> <엔티티, PK>

    WebUser findByUsername(String name); //findby username(id)

    boolean existsByUsername(String name); //findby username
}
