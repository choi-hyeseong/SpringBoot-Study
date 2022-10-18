package com.division.springbootstudy.controller;

import com.division.springbootstudy.component.FileHandler;
import com.division.springbootstudy.domain.WebUser;
import com.division.springbootstudy.dto.*;
import com.division.springbootstudy.service.BoardService;
import com.division.springbootstudy.service.CustomUserDetailService;
import com.division.springbootstudy.service.FileService;
import com.division.springbootstudy.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller //localhost:8080/~ 오는 요청들 파싱해주는곳, restcontroller 사용시 string 리턴값 => body
@AllArgsConstructor
public class ApplicationController {

    //private UserService service;
    private CustomUserDetailService service;
    private BoardService boardService;
    private MemberService memberService; //멤버로 분리시키기 위함
    private FileService fileService;
    private FileHandler handler;

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
    public String detail(@AuthenticationPrincipal WebUser user, @RequestParam long id, Model model) { //id가 없는경우 bad request
        BoardVO vo = boardService.getBoardById(id);
        model.addAttribute("board",vo);
        model.addAttribute("current_user", user.getUsername());
        model.addAttribute("files", vo.getFile());
        model.addAttribute("replies", vo.getReplies());
        return "detail";
    }

    @GetMapping("/board/write")
    public String write() {
        return "write";
    }

    @PostMapping("/board/write")
    public void writeBoard(@AuthenticationPrincipal WebUser user, BoardDto dto, HttpServletResponse response, @RequestParam(value = "file", required = false) List<MultipartFile> file) throws IOException {
        if (file != null && file.size() != 0)
            boardService.writeText(dto, user.getUsername(), handler.save(file));
        System.out.println("글 작성됨, " + dto);
        response.sendRedirect("/board"); //글 작성이후 redirect (안하면 중복 제출)
    }

    @DeleteMapping("/board/reply/delete")
    public ResponseEntity<Integer> deleteReply(@AuthenticationPrincipal WebUser user, @RequestParam long id) {
        boolean result = boardService.deleteReply(user.getUsername(), id);
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/board/reply/write")
    public ResponseEntity<Integer> writeReply(@AuthenticationPrincipal WebUser user, @RequestBody ReplyDto dto) {
        System.out.println(dto + " 댓글 작성됨");
        boardService.writeReply(dto, user.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/delete")
    public ResponseEntity<Integer> deleteBoard(@AuthenticationPrincipal WebUser user, @RequestParam long id) {
        BoardVO result = boardService.getBoardById(id);
        boolean isExist = false;
        if (result.getUser().getUsername().equals(user.getUsername())) {
            //사용자가 일치할경우
            isExist = boardService.deleteBoardById(id);
        }
        return isExist ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/board/edit")
    public ResponseEntity<Integer> editBoard(@AuthenticationPrincipal WebUser user, BoardDto dto, long id) {
        boardService.editBoard(user.getUsername(), dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/board/edit")
    public String edit(@AuthenticationPrincipal WebUser user, Model model, long id) {
        BoardVO result = boardService.getBoardById(id);
        if (result.getUser().getUsername().equals(user.getUsername())) {
            model.addAttribute("board", boardService.getBoardById(id));
        }
        return "edit";
    }

    @GetMapping("/board/image")
    public ResponseEntity<Resource> image(@RequestParam long id) {
        try {
            FileResponseDto dto = fileService.getFileById(id);
            Resource image = handler.fileDtoToResource(dto);
            if (!image.exists())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", Files.probeContentType(handler.getFilePathFromDto(dto)));
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
