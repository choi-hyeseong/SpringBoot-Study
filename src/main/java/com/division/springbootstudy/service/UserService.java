package com.division.springbootstudy.service;

import com.division.springbootstudy.domain.User;
import com.division.springbootstudy.dto.UserDto;
import com.division.springbootstudy.dto.UserResponseDto;
import com.division.springbootstudy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public long addUser(UserDto dto) {
        return userRepository.save(dto.toEntity())
                             .getId(); //db에 저장후 id값 반환
    }

    //빌더 안쓰고, User를 생성자 파라미터로 받는 responsedto 사용
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                             .stream()
                             .map(UserResponseDto::new)
                             .collect(Collectors.toList());
    }

}
