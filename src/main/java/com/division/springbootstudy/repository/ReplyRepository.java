package com.division.springbootstudy.repository;

import com.division.springbootstudy.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
