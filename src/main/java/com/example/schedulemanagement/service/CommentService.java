package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.CommentRequestDto;
import com.example.schedulemanagement.dto.CommentResponseDto;
import com.example.schedulemanagement.entity.Comment;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.CommentRepository;
import com.example.schedulemanagement.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        long commentCount = commentRepository.countByScheduleId(scheduleId);
        if (commentCount >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지만 작성할 수 있습니다.");
        }
        Comment comment = new Comment(
                scheduleId,
                requestDto.getContent(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getScheduleId(),
                savedComment.getContent(),
                savedComment.getWriter(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}

