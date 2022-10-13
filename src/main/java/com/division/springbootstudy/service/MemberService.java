package com.division.springbootstudy.service;

import com.division.springbootstudy.dto.UserRegisterDto;
import com.division.springbootstudy.dto.UserResponseDto;
import com.division.springbootstudy.repository.AuthRepository;
import com.division.springbootstudy.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository repository;

    @Transactional
    public void save(UserRegisterDto dto) {
        repository.save(dto.toMemberEntity());
    }


}
