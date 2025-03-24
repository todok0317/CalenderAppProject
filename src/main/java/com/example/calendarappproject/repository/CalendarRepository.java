package com.example.calendarappproject.repository;

import com.example.calendarappproject.dto.CalendarResponseDto;
import com.example.calendarappproject.entity.Calendar;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    CalendarResponseDto saveToDo (Calendar calender);

    List<CalendarResponseDto> findAllCalendars();

    Optional<Calendar> findCalendarById(Long id);

    Calendar findCalendarByIdOrElseThrow(Long id);

    int updateCalendar(Long id, String author, String todo);
}
