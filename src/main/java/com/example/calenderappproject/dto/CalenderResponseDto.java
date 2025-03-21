package com.example.calenderappproject.dto;

import com.example.calenderappproject.entity.Calender;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalenderResponseDto {

    private String toDo;
    private String title;
    private String author;
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public CalenderResponseDto (Calender calender){
        this.toDo = calender.getToDo();
        this.title = calender.getTitle();
        this.author = calender.getAuthor();
        this.id = calender.getId();
        this.createdAt = calender.getCreatedAt();
        this.updateAt = calender.getUpdateAt();

    }

}
