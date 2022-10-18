package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.FileEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileDto {

    private String fileName;

    private String modifiedName;

    public FileEntity toEntity(Board board) {
        return FileEntity.builder()
                         .fileName(fileName)
                         .modifiedName(modifiedName)
                         .board(board)
                         .build();
    }
}
