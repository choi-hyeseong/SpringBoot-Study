package com.division.springbootstudy.service;

import com.division.springbootstudy.dto.FileDto;
import com.division.springbootstudy.dto.FileResponseDto;
import com.division.springbootstudy.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FileService {

    private FileRepository repository; //레포지토리에 저장해야지...

    @Transactional
    public FileResponseDto getFileById(Long id) {
        return new FileResponseDto(repository.findById(id).orElseThrow());
    }

    @Transactional
    public void save(FileDto dto) {
        repository.save(dto.toEntity());
    }

}
