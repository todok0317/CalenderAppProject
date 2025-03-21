package com.example.calenderappproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Calender {

    private String toDo;
    private String title;
    private String author;
    private long id;
    private long password;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
