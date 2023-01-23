package com.organization.mynote;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Note {

    private int id;
    private String name;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public Note(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @NonNull
    @Override
    public String toString() {
        return id +
                " / " + name +
                " / " + content +
                " / " + creationDate +
                " / " + updateDate;
    }
}
