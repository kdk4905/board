package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //Get 매핑
    // /save 화면을 가져오는 요청을 수행함
    @GetMapping("/save")
    public String save()
    {
        return "save";
    }

    //Post 매핑
    //데이터를 제출하는 요청 연결
    @PostMapping("/save")
    public String save(BoardDTO boardDTO)
    {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    //Model: Spring이 제공하는 interface
    public String findAll(Model model)
    {
        List<BoardDTO> boardDTOList = boardService.findAll();
        //?
        //처음엔 안되었는데 지금은 잘되네. 왜지? 모름...
        model.addAttribute("boardList", boardDTOList);
        System.out.println("boardDTOList = " + boardDTOList);
        return "List";
    }

    // /10, /1
    @GetMapping("/{id}")
    public  String findById(@PathVariable("id") Long id, Model model)
    {
        //조회수 처리
        boardService.updateHits(id);
        //상세내용 처리
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        System.out.println("boardDTO = " + boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model)
    {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return  "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO, Model model)
    {
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
    }
}
