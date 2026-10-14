package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String writer;
    private String password;
}
