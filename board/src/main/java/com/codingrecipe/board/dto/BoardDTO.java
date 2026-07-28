package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

//0728
import java.util.List;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;
    //0727 추가
    private int fileAttached;
    //0728 추가
    //스프링에서 제공하는 인터페이스
    private List<MultipartFile> boardFile;
}
