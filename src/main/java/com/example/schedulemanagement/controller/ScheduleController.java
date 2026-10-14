package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto result = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        ScheduleResponseDto result = scheduleService.getSchedule(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules() {
        List<ScheduleResponseDto> result = scheduleService.getSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto result = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        scheduleService.deleteSchedule(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).build(); // 또는 HttpStatus.NO_CONTENT 사용 가능
    }
}