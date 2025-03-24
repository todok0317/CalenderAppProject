package com.example.calendarappproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    private String author;
    private String password;
    private String title;
    private String todo;
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Calendar(String author, String password, String title, String todo) {
        this.author = author;
        this.password = password;
        this.title = title;
        this.todo = todo;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Calendar(long id, String author, String todo, String password) {
        this.id = id;
        this.author = author;
        this.todo = todo;
        this.password = password;
    }
}
