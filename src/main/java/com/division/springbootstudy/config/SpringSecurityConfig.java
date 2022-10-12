package com.division.springbootstudy.config;

import com.division.springbootstudy.handler.AuthFailHandler;
import com.division.springbootstudy.handler.AuthSuccessHandler;
import com.division.springbootstudy.service.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SpringSecurityConfig {

    private CustomUserDetailService service; //유저 정보 가져오는곳
    private AuthFailHandler failHandler;
    private AuthSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf() //이거 비활성화 해줘야 post 요청됨.. 칫
            .disable()
            .headers()
            .frameOptions()
            .disable()//h2-console
            .and()
            .userDetailsService(service)
            .authorizeRequests(auth -> auth.mvcMatchers("/login")
                                           .permitAll()
                                           .mvcMatchers("/main")
                                           .permitAll()
                                           .mvcMatchers("/register")
                                           .permitAll()
                                           .antMatchers("/h2-console/**")
                                           .permitAll()
                                           .antMatchers("/board")
                                           .hasRole("user")
                                           .anyRequest()
                                           .authenticated())

            .formLogin(login -> login.loginPage("/login") //폼 형식 로그인
                                     .usernameParameter("username") //username : ~
                                     .passwordParameter("password") //password : ~
                                     .loginProcessingUrl("/login")
                                     .successHandler(successHandler)
                                     .failureHandler(failHandler)
                                     .permitAll())
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true); //로그아웃 성공시

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
