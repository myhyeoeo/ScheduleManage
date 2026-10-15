package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CommentRequestDto;
import com.example.schedulemanagement.dto.CommentResponseDto;
import com.example.schedulemanagement.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CommentRequestDto requestDto
    ){
        CommentResponseDto responseDto = commentService.createComment(scheduleId,requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
