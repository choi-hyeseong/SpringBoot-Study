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
import org.springframework.web.servlet.ModelAndView;

@Controller //localhost:8080/~ 오는 요청들 파싱해주는곳, restcontroller 사용시 string 리턴값 => body
public class ApplicationController {

    /*@Autowired
    private UserRepository repository; 아직 안쓸예정*/

    /* Thymeleaf인가 뭐시기인가 쓴거
    @GetMapping("/test") //localhost:8080/main
    public String main(@ModelAttribute UserDto dto) { //@RequestParam String name -> /main?name=~일때 name 변수에 집어넣어줌 - get
        System.out.println(dto.getName() + " " + dto.getAge()); //이런식으로 ModelAttribute를 써서 dto형식으로 받아올 수 있음. -> JSON 파싱
        return "main"; //resources -> templates -> main.html 로 치환된다고 하는데 안되는듯..
    }
    */

    //위에꺼는 그냥 intellij랑 충돌생겨서 생긴것같음.. jsp는 잘되네..
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("data", "hello");
        return "main";
    }


}
