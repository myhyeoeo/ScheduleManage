package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.CommentResponseDto;
import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Comment;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.CommentRepository;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, CommentRepository commentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getWriter(),
                requestDto.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("선택한 일정이 존재하지 않습니다")
        );

        List<Comment> comments = commentRepository.findByScheduleId(id);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDto dto = new CommentResponseDto(
                    comment.getId(),
                    comment.getScheduleId(),
                    comment.getContent(),
                    comment.getWriter(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            commentResponseDtos.add(dto);
        }

        return new ScheduleResponseDto(
                schedule,
                commentResponseDtos
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> responseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responseDtos.add(new ScheduleResponseDto(schedule));
        }
        return responseDtos;
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정을 찾을 수 없습니다.")
        );
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.update(requestDto.getTitle(), requestDto.getWriter());
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("선택한 일정을 찾을 수 없습니다.")
        );
        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
