package com.codingrecipe.board.controller;

//자동으로 추가된 코드
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//어노테이션 선언과 동시에 알아서 코드가 추가됌.
@Controller
public class HomeController {
    //메서드 정의
    @GetMapping("/")
    public String index()
    {
        System.out.println("HomeController.index");
        return "index";
    }
}
