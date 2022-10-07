package com.division.springbootstudy.repository;

import com.division.springbootstudy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //repo 자동 생성 -> <엔티티, PK>
}
