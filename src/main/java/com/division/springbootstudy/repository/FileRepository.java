package com.division.springbootstudy.repository;

import com.division.springbootstudy.domain.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
