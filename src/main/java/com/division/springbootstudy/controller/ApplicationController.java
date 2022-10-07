package com.division.springbootstudy.controller;

import com.division.springbootstudy.domain.User;
import com.division.springbootstudy.dto.UserDto;
import com.division.springbootstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //localhost:8080/~ 오는 요청들 파싱해주는곳, 왜 Controller 쓸때는 안되고 얘는 되냐
public class ApplicationController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/test") //localhost:8080/test
    public String main(@ModelAttribute UserDto dto) { //@RequestParam String name -> /main?name=~일때 name 변수에 집어넣어줌 - get
        System.out.println(dto.getName() + " " + dto.getAge()); //이런식으로 ModelAttribute를 써서 dto형식으로 받아올 수 있음.
        return "main"; //resources -> templates -> main.html 로 치환된다고 하는데 안되는듯..
    }


}
