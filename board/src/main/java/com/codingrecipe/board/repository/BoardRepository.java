package com.codingrecipe.board.repository;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    //Mybatis 연계해야 자동으로 생성이 가능함
    private final SqlSessionTemplate sql;
    public BoardDTO save(BoardDTO boardDTO)
    {
        sql.insert("Board.save", boardDTO);
        return boardDTO;
    }

    public List<BoardDTO> findAll()
    {
        //state먼트는 내가 작성하는게 아님.
        //인텔리 제이에서 자동으로 완성해 주는 코드
        //직관적으로 보고 이해할 수 있게 도와주는 기능인듯?
        //"Board.findAll"에서 "Board.findAll()"이면 오류남
        //뭐야 도대체
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id)
    {
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id)
    {
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDTO boardDTO)
    {
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id)
    {
        sql.delete("Board.delete", id);
    }

    public void saveFile(BoardFileDTO boardFileDTO)
    {
        sql.insert("Board.saveFile", boardFileDTO);
    }

    //0728 파일 조회 메서드
    public BoardFileDTO findFile(Long id)
    {
        return sql.selectOne("Board.findFile", id);
    }
}
