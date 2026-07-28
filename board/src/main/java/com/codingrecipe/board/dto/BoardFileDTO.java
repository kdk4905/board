package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
    private Long id;
    private Long boardId;
    //원본 파일의 이름
    private String originalFileName;
    //DB에 저장된 파일 이름
    private String storedFileName;
}
