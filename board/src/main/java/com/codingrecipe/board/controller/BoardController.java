package com.codingrecipe.board.controller;
//0909 장준환 TEST
import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
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
    public String save(BoardDTO boardDTO) throws IOException {
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
        //0728 파일조회 메서드
        if (boardDTO.getFileAttached() == 1)
        {
            List<BoardFileDTO> boardFileDTOList = boardService.findFile(id);
            //07.30 다중파일 처리를 위한 주석
            //BoardFileDTO boardFileDTO = boardService.findFile(id);
            //07.29 디버깅을 위한 로그 삽입. 문제 해결후 주석처리
            //System.out.println("boardFileDTO = " + boardFileDTO);
            model.addAttribute("boardFileList", boardFileDTOList);
        }
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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        boardService.delete(id);
        return "redirect:/list";
    }
}
