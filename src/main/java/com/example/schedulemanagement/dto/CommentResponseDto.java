package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final Long scheduledId;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Long id, Long scheduledId, String content, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduledId = scheduledId;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
