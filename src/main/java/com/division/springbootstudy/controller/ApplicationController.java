package com.division.springbootstudy.controller;

import com.division.springbootstudy.domain.WebUser;
import com.division.springbootstudy.dto.BoardDto;
import com.division.springbootstudy.dto.UserRegisterDto;
import com.division.springbootstudy.service.BoardService;
import com.division.springbootstudy.service.CustomUserDetailService;
import com.division.springbootstudy.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller //localhost:8080/~ 오는 요청들 파싱해주는곳, restcontroller 사용시 string 리턴값 => body
@AllArgsConstructor
public class ApplicationController {

    //private UserService service;
    private CustomUserDetailService service;
    private BoardService boardService;
    private MemberService memberService; //멤버로 분리시키기 위함

    /* Thymeleaf인가 뭐시기인가 쓴거
    @GetMapping("/test") //localhost:8080/main
    public String main(@ModelAttribute UserDto dto) { //@RequestParam String name -> /main?name=~일때 name 변수에 집어넣어줌 - get
        System.out.println(dto.getName() + " " + dto.getAge()); //이런식으로 ModelAttribute를 써서 dto형식으로 받아올 수 있음. -> JSON 파싱
        return "main"; //resources -> templates -> main.html 로 치환된다고 하는데 안되는듯..
    }
    */

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login/error") //에러페이지로 온거 바인딩
    public String loginError(@RequestAttribute String error, Model model) {
        model.addAttribute("error", error); //Request Attribute -> model
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody UserRegisterDto dto) {
        //회원가입
        System.out.println(dto.toString());
        if (service.isUserNameExist(dto.getUsername()))
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else {
            service.save(dto); //auth
            memberService.save(dto); //member
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //위에꺼는 그냥 intellij랑 충돌생겨서 생긴것같음.. jsp는 잘되네..
    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("data", "hello");
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext()
                                                                                          .getAuthentication());
        return "login"; //로그인페이지로 이동, redirect 쓰는것도 좋을듯
    }

    //board controller 로 분리가능
    @GetMapping("/board")
    public String board(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        return "board";
    }

    @GetMapping("/board/detail")
    public String detail(@RequestParam long id, Model model) { //id가 없는경우 bad request
        model.addAttribute("board",boardService.getBoardById(id));
        return "detail";
    }

    @PostMapping("/board/write")
    public ResponseEntity<Integer> writeBoard(@AuthenticationPrincipal WebUser user, @RequestBody BoardDto dto) {
        boardService.writeText(dto, user.getUsername());
        System.out.println("글 작성됨, " + dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @GetMapping("/user/add")
    public ResponseEntity<Long> addUser(@ModelAttribute UserDto dto) {
        return new ResponseEntity<>(service.addUser(dto), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> getUser() {
        return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
    }
     */

}
