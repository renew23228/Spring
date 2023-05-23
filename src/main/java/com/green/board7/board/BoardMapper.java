package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // 기능이 많음. 그 중 하나는 BoardMapper 인터페이스와 BoardMapper.xml을 맵핑 시켜줌
public interface BoardMapper {
    int insBoard(BoardInsDto dto); // 영향 받은 행 수가 return됨 //int말고 void적으면 안줌
    List<BoardVo> selBoardAll(BoardDto dto); //
    BoardDetailVo selBoardById(BoardDto dto);
    int updBoard(BoardDto dto);
    int delBoard(BoardDto dto);
}
