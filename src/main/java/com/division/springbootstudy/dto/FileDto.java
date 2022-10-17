package com.division.springbootstudy.dto;

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

    public FileEntity toEntity() {
        return FileEntity.builder()
                        .fileName(fileName)
                        .modifiedName(modifiedName)
                        .build();
    }
}
