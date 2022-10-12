package com.division.springbootstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity //엔티티객체 = db에서 바로 매핑
@NoArgsConstructor //생성자 없음
@AllArgsConstructor
@Table(name = "user_db")
@Builder
@Getter
public class WebUser implements UserDetails { //userDetail을 구현함으로써 user커스텀 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1씩 증가
    private Long id;

    private String name;

    private Integer age;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
