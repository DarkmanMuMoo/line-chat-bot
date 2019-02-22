package com.mumoo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("userToken")
public class Token {
    @Id
    private String token;

    private LocalDateTime created;

    private LocalDateTime expireAt;

    private Long userId;


    public  Token(){

    }

    public Token(String token, LocalDateTime created, LocalDateTime expireAt, Long userId) {
        this.token = token;
        this.created = created;
        this.expireAt = expireAt;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
