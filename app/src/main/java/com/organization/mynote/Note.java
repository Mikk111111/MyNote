package com.organization.mynote;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(tableName = Constant.ENTITY_NOTE_TABLE)
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int id;
    @ColumnInfo(name = "note_name")
    private String name;
    @ColumnInfo(name = "note_content")
    private String content;

    @Ignore
    //@ColumnInfo(name = "note_create_date")
    private LocalDateTime createDate;
    @Ignore
   // @ColumnInfo(name = "note_update_date")
    private LocalDateTime updateDate;

    @Ignore
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Note(String name, String content) {
        this.name = name;
        this.content = content;
//        this.createDate = LocalDateTime.now();
//        this.updateDate = LocalDateTime.now();
    }

    @Ignore
    public Note(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
//        this.createDate = LocalDateTime.now();
//        this.updateDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public LocalDateTime getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }
//
//    public LocalDateTime getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(LocalDateTime updateDate) {
//        this.updateDate = updateDate;
//    }

    @Override
    public String toString() {
        return String.format(
                "%s | %s %n %s | %s%n %s",
                id,
                name,
                content,
                createDate == null ? "" : createDate.format(formatter),
                updateDate == null ? "" : updateDate.format(formatter)
        );
    }
}
