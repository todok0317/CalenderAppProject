package com.example.calendarappproject.controller;


import com.example.calendarappproject.dto.CalendarRequestDto;
import com.example.calendarappproject.dto.CalendarResponseDto;
import com.example.calendarappproject.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
public class CalendarController {

    private final CalendarService calendarService;


    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 할 일 생성
    @PostMapping
    public ResponseEntity<CalendarResponseDto> createToDo(@RequestBody CalendarRequestDto dto){

        return new ResponseEntity<>(calendarService.saveToDo(dto), HttpStatus.CREATED);
    }
    // 할 일 목록 전체 조회
    @GetMapping
    public List<CalendarResponseDto> findAllCalendars() {
        return calendarService.findAllCalendars();
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findCalendarById(@PathVariable Long id) {
        return new ResponseEntity<>(calendarService.findCalendarById(id), HttpStatus.OK);
    }

    // 선택한 할 일 수정
    @PostMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> updateCalendar(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto requestDto,
            @RequestParam String password // 비밀번호 요청 파라미터로 받기
    ){
        return new ResponseEntity<>(calendarService.updateCalendarById(id, requestDto.getAuthor(), requestDto.getTodo(), password), HttpStatus.OK);

    }

    // 선택한 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalender(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
