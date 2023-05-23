package com.green.board7.board.model;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard;

    private String title;
    private String ctnt;
    private String writer;
    private String createdAt; // Date타입 써도됨. JPA를 쓰게되면 Date타입으로 바꿀거임
    private String updatedAt; // Date타입 써도됨
}
