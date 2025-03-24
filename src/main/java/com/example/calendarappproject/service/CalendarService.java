package com.example.calendarappproject.service;

import com.example.calendarappproject.dto.CalendarRequestDto;
import com.example.calendarappproject.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {

    CalendarResponseDto saveToDo(CalendarRequestDto requestDto);

    List<CalendarResponseDto> findAllCalendars();

    CalendarResponseDto findCalendarById(Long id);

    CalendarResponseDto updateCalendarById(Long id, String author, String todo, String password);



}
