package com.example.calendarappproject.dto;

import com.example.calendarappproject.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {

    private long id;
    private String author;
    private String password;
    private String title;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.author = calendar.getAuthor();
        this.password = calendar.getPassword();
        this.title = calendar.getTitle();
        this.todo = calendar.getTodo();
        this.createdAt = calendar.getCreatedAt();
        this.updatedAt = calendar.getUpdatedAt();
    }

}
