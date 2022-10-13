package com.division.springbootstudy.repository;

import com.division.springbootstudy.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
