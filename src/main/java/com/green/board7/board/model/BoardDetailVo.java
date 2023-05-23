package com.green.board7.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //iboard, title, ctnt, writer, createdAt, updatedAt을 받는 생성자를 만들겠다는 의미
public class BoardDetailVo {
    private int iboard;

    private String title;
    private String ctnt;
    private String writer;
    private String createdAt;
    private String updatedAt;
}
