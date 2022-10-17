package com.division.springbootstudy.component;

import com.division.springbootstudy.domain.FileEntity;
import com.division.springbootstudy.dto.FileDto;
import com.division.springbootstudy.dto.FileResponseDto;
import com.division.springbootstudy.service.FileService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class FileHandler {

    private static final String DIR_LOCATION = System.getProperty("user.dir") + "\\files";

    public List<FileDto> save(List<MultipartFile> fileList) {
        List<FileDto> entityList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String origin = file.getOriginalFilename();
            String ext = extractExtWithDot(origin);
            String modifiedName = UUID.randomUUID() + ext;
            String savePath = DIR_LOCATION + "\\" + modifiedName;
            if (!new File(DIR_LOCATION).exists())
                new File(DIR_LOCATION).mkdir();
            try {
                FileDto dto = new FileDto(origin, modifiedName);
                file.transferTo(new File(savePath));
                entityList.add(dto);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        return entityList;
    }

    public Resource fileDtoToResource(FileResponseDto dto) throws IOException {
        return new InputStreamResource(Files.newInputStream(Paths.get(DIR_LOCATION + "\\" + dto.getModifiedName())));
    }

    public String extractExtWithDot(String origin) {
        return "." + origin.substring(origin.lastIndexOf(".") + 1);
    }

    public Path getFilePathFromDto(FileResponseDto dto) {
        return Path.of(DIR_LOCATION + "\\" + dto.getModifiedName());
    }
}
