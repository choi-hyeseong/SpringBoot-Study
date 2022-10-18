package com.division.springbootstudy.service;

import com.division.springbootstudy.domain.Board;
import com.division.springbootstudy.domain.Member;
import com.division.springbootstudy.domain.Reply;
import com.division.springbootstudy.domain.WebUser;
import com.division.springbootstudy.dto.BoardDto;
import com.division.springbootstudy.dto.BoardVO;
import com.division.springbootstudy.dto.FileDto;
import com.division.springbootstudy.dto.ReplyDto;
import com.division.springbootstudy.repository.BoardRepository;
import com.division.springbootstudy.repository.MemberRepository;
import com.division.springbootstudy.repository.ReplyRepository;
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
    private ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<BoardVO> getBoardList() {
        return repository.findAll().stream().map(BoardVO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardVO getBoardById(Long id) {
        return new BoardVO(repository.findById(id).orElseGet(() -> new Board((long)-1, new Member((long)-1, "null", "null", "null", -1), "null", "null", null, null)));
    }

    @Transactional
    public boolean deleteBoardById(Long id) {
        boolean isExist = repository.existsById(id);
        if (isExist) {
            Board board = repository.findById(id).orElseThrow(); //무조건 얻음
            repository.delete(board);
        }
        return isExist;
    }

    @Transactional
    public void writeText(BoardDto dto, String username, List<FileDto> files) {
        Member member = memberRepository.findByUsername(username);
        dto.setMember(member);
        Board board = dto.toEntity();
        for (FileDto fileDto : files) {
            board.getFiles().add(fileDto.toEntity(board));
        }
        repository.save(board);
    }

    @Transactional
    public void editBoard(String username, BoardDto dto, Long id) {
        Board board = repository.findById(id).orElseThrow();
        if (board.getMember().getUsername().equals(username))
            board.update(dto.getTitle(), dto.getText());
    }

    @Transactional
    public void writeReply(ReplyDto dto, String username) {
        Board board = repository.findById(dto.getBoardId()).orElseThrow();
        dto.setMember(memberRepository.findByUsername(username)); //member insert
        Reply reply = dto.toEntity();
        board.addReply(reply);
    }

    @Transactional
    public boolean deleteReply(String username, long id) {
        boolean result = false;
        Reply reply = replyRepository.findById(id).orElseThrow();
        if (username.equals(reply.getMember().getUsername())) {
            Board board = reply.getBoard();
            if (board != null)
                board.removeReply(reply);
            replyRepository.deleteById(id);
            result = true;
        }
        return result;

    }
}
