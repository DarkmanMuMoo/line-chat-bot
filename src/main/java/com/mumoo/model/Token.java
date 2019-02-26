package com.mumoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("usertoken")
public class Token implements Persistable<String> {
    @Id
    private String token;

    private LocalDateTime created;

    private LocalDateTime expireAt;

    private Long userId;

    @Transient
    @JsonIgnore
    private boolean newEntity;

    public Token() {

    }

    public Token(String token, LocalDateTime created, LocalDateTime expireAt, Long userId) {
        this.token = token;
        this.created = created;
        this.expireAt = expireAt;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @JsonIgnore
    public String getId() {
        return token;
    }

    @Override
    public boolean isNew() {
        return newEntity;
    }

    public void setNewEntity(boolean newEntity) {
        this.newEntity = newEntity;
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
