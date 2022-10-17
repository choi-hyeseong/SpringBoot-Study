package com.division.springbootstudy.dto;

import com.division.springbootstudy.domain.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class FileResponseDto {

    private String fileName;

    private String modifiedName;

    public FileResponseDto(FileEntity entity) {
        this.fileName = entity.getFileName();
        this.modifiedName = entity.getModifiedName();
    }
}
