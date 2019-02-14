package com.mumoo.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Task {
    @Id
    private Long id;
    private String title;
    private LocalDateTime created;
    private Boolean done;
    private Boolean important;
    private Long userId;

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean isImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Task() {
    }

    public Task(Long id, String title, LocalDateTime created, Boolean done, Boolean important, Long userId) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.done = done;
        this.important = important;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", done=" + done +
                ", important=" + important +
                ", userId=" + userId +
                '}';
    }
}
