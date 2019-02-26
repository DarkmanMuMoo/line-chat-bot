package com.mumoo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("userInfo")
public class User {
    @Id
    private Long id;
    @Column("lineId")
    private String lineId;
    private LocalDateTime created;


    public User(String lineId, LocalDateTime created) {
        this.lineId = lineId;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lineId='" + lineId + '\'' +
                ", created=" + created +
                '}';
    }
}
