package com.division.springbootstudy.service;

import com.division.springbootstudy.domain.UserRole;
import com.division.springbootstudy.domain.WebUser;
import com.division.springbootstudy.dto.UserDto;
import com.division.springbootstudy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService { //-> principle등 인증에 필요한 서비스(user) 리턴, AuthenticationProvider 등록안할시 자동으로 생성됨.

    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("finding.." + username);
        WebUser user = repository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username); //못찾을경우 throw
        return user;
    }

    @Transactional
    public Long save(UserDto dto) {
        //입력받은 dto를 UserDetail로 변경후 저장
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword())); //비밀번호 암호화후 저장
        dto.setRole(UserRole.USER); //기본 유저
        return repository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public boolean isUserNameExist(String name) {
        return repository.existsByUsername(name);
    }
}
