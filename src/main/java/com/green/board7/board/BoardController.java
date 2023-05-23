package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import java.util.List;



@Tag(name="게시판", description = "설명")
@RestController
@Validated // 유효성 검사
@RequestMapping("/board") //우리 서비스엔 board밖에 없다면 생략
public class BoardController {
    private final Logger LOGGER;
    private final BoardService service;

    @Autowired //스프링이 가지고 있는 객체 주소 값을 달라고 하는거(DI)
    public BoardController(BoardService service) {
        LOGGER = LoggerFactory.getLogger(BoardController.class);
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "글등록", description = "" +
            "title(string): 제목 <br>"+
            "ctnt(string): 내용 <br>"+
            "writer(string): 작성자")
    public int postBoard(@RequestBody BoardInsDto dto) {
        LOGGER.warn("경고, 글 등록이 됩니다.");
        return service.insBoard(dto);
    }

    @GetMapping
    public List<BoardVo> getBoardAll(@RequestParam @Min(value=1, message = "page값은 1 이상 이어야 합니다.") int page
            , @RequestParam(defaultValue = "30") int row) { //requestParam이 없으면 page값, row값 둘다 무조건 받아야함
        LOGGER.info("page : " + page);
        BoardDto dto = new BoardDto();
        dto.setPage(page);
        dto.setRow(row); // 입력한 페이지당 글 수
        return service.selBoardAll(dto);
    }

    @GetMapping("/{iboard}")
    public BoardDetailVo getBoardById(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);
        LOGGER.info(dto.toString());
        return service.selBoardById(dto);
    }

    @PutMapping
    public int putBoard(@RequestBody BoardDto dto) {
        return service.updBoard(dto);
    }

    @DeleteMapping("/{iboard}")
    public int deleteBoard(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);

        return service.delBoard(dto);
    }
}


/* 이름 형식은 회사마다 다름
Vo
Dto : 프론트에서 서버로 넘어오는 친구
Entity : 데이터베이스 테이블이랑 구조가 똑같음
Vo : 백엔드에서 프론트로 보내는 친구

*/