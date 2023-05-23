package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // bean 등록. 스프링 컨테이너가 객체 생성해줌
public class BoardService {
    private final BoardMapper mapper;

    @Autowired //스프링이 가지고 있는 객체 주소 값을 달라고 하는거(DI)
    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    public int insBoard(BoardInsDto dto) {
        return mapper.insBoard(dto);
    }

    public List<BoardVo> selBoardAll(BoardDto dto) {

        final int ROW_PER_PAGE = 30;
        int startIdx = (dto.getPage()-1) * dto.getRow();
        dto.setStartIdx(startIdx);
        dto.setRowLen(dto.getRow());
        return mapper.selBoardAll(dto);

        // page*(1-page) , rowLen*page
        // BoardDto ( page, startIdx, rowLen )
        // limit ${startIdx}, ${rowLen}
        // 한페이지당 글 수*(1-page값) , 한페이지당 글 수
    }

    public BoardDetailVo selBoardById(BoardDto dto) {
        return mapper.selBoardById(dto);
    }

    public int updBoard(BoardDto dto) {
        return mapper.updBoard(dto);
    }

    public int delBoard(BoardDto dto) {
        return mapper.delBoard(dto);
    }
}
