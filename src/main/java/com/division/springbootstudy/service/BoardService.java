package com.division.springbootstudy.service;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.WebUser;
import com.division.springbootstudy.dto.BoardDto;
import com.division.springbootstudy.dto.BoardVO;
import com.division.springbootstudy.dto.FileDto;
import com.division.springbootstudy.repository.BoardRepository;
import com.division.springbootstudy.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {

    private BoardRepository repository;
    private MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<BoardVO> getBoardList() {
        return repository.findAll().stream().map(BoardVO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardVO getBoardById(Long id) {
        return new BoardVO(repository.findById(id).orElseGet(() -> new Board((long)-1, new Member((long)-1, "null", "null", "null", -1), "null", "null", null)));
    }

    @Transactional
    public boolean deleteBoardById(Long id) {
        boolean isExist = repository.existsById(id);
        if (isExist)
            repository.deleteById(id);
        return isExist;
    }

    @Transactional
    public void writeText(BoardDto dto, String username, List<FileDto> files) {
        Member member = memberRepository.findByUsername(username);
        dto.setMember(member);
        dto.setFileList(files);
        repository.save(dto.toEntity());
    }

    @Transactional
    public void editBoard(BoardDto dto, Long id) {
        Board board = repository.findById(id).orElseThrow();
        board.update(dto.getTitle(), dto.getText());
    }
}
