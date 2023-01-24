package com.organization.mynote.repository;

import android.provider.SyncStateContract;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.organization.mynote.Constant;
import com.organization.mynote.Note;

import java.util.List;
@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constant.ENTITY_NOTE_TABLE)
    List<Note> getAll();

    @Query("SELECT * FROM " + Constant.ENTITY_NOTE_TABLE + " WHERE note_id = :id")
    Note getItem(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotes(List<Note> notes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    public void deleteNote(Note note);

    @Delete
    public void deleteNotes(List<Note> notes);

    @Query("DELETE FROM " + Constant.ENTITY_NOTE_TABLE + " WHERE note_id =:id")
    void deleteItem(int id);
}
