package com.example.calendarappproject.service;

import com.example.calendarappproject.dto.CalendarRequestDto;
import com.example.calendarappproject.dto.CalendarResponseDto;
import com.example.calendarappproject.entity.Calendar;
import com.example.calendarappproject.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // 일정 생성
    @Override
    public CalendarResponseDto saveToDo(CalendarRequestDto requestDto) {

        Calendar calendar= new Calendar(
                requestDto.getAuthor(),
                requestDto.getPassword(),
                requestDto.getTitle(),
                requestDto.getTodo()
        );

        return calendarRepository.saveToDo(calendar);
    }
    // 할 일 단체 조회
    @Override
    public List<CalendarResponseDto> findAllCalendars() {

        List<CalendarResponseDto> allCalendars = calendarRepository.findAllCalendars();
        return allCalendars;
    }
    // 선택한 일정 조회
    @Override
    public CalendarResponseDto findCalendarById(Long id) {

        Calendar calendar = calendarRepository.findCalendarByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }
    // 선택한 일정 수정
    @Transactional
    @Override
    public CalendarResponseDto updateCalendarById (Long id, String author, String todo, String password) {
        // 비밀번호 확인
        Calendar calendar = calendarRepository.findCalendarByIdOrElseThrow(id);
        if(!calendar.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
        // 작성자와 할 일 내용이 없으면 예외를 던짐
        if(author == null || todo != null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자 그리고 할 일 내용이 없습니다.");
        }
        // 일정 수정
        int updateRow = calendarRepository.updateCalendar(id, author, todo);
        // 수정된 것이 없으면 예외를 던짐
        if(updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정 된 것이 없습니다");
        }
        // 수정 된 일정 반환
        calendar = calendarRepository.findCalendarByIdOrElseThrow(id);
        return new CalendarResponseDto(calendar);
    }


}
