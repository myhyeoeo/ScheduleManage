package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponseDto> comments;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.comments = null;
    }

    public ScheduleResponseDto(Schedule schedule, List<CommentResponseDto> comments) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
        this.comments = comments;
    }
}
