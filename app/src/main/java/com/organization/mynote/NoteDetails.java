package com.organization.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.organization.mynote.repository.MainDatabase;
import com.organization.mynote.repository.NoteDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NoteDetails extends AppCompatActivity {

    NoteDao noteDao;
    TextView noteIdTextView;
    TextView noteNameEditText;
    TextView noteContentEditText;
    TextView noteCreationDateTextView;
    TextView noteUpdateDateTextView;
    Button button;
    Note note = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpViews();

        noteDao = MainDatabase.getRoomInstance(getApplicationContext()).noteDao();

        if(getIntent().getExtras()!=null){
            int noteId = getIntent().getIntExtra(Constant.KEY_NOTE_ID,0);
            gteNoteDetailsById(noteId);
//          getIntent().getStringExtra(Constant.KEY_NOTE_DETAILS);
        }

        setUpClickSaveButton();
    }

    private void setUpViews() {
        setContentView(R.layout.activity_note_details);
        noteIdTextView = findViewById(R.id.noteIdTextView);
        noteNameEditText= findViewById(R.id.noteNameEditText);
        noteContentEditText= findViewById(R.id.noteContentEditText);
        noteCreationDateTextView= findViewById(R.id.noteCreationDateTextView);
        noteUpdateDateTextView= findViewById(R.id.noteUpdateDateTextView);
        button= findViewById(R.id.saveButton);
    }

    private void gteNoteDetailsById(int noteId) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        note = noteDao.getItem(noteId);

        noteIdTextView.setText(note.getId().toString());
        noteNameEditText.setText(note.getName());
        noteContentEditText.setText(note.getContent());
        noteCreationDateTextView.setText(note.getCreateDate().format(formatter));
        noteUpdateDateTextView.setText(note.getUpdateDate().format(formatter));
    }

    private void setUpClickSaveButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setName(noteNameEditText.getText().toString());
                note.setContent(noteContentEditText.getText().toString());
                note.setUpdateDate(LocalDateTime.now());
                if(note.getId()==null){
                    note.setCreateDate(LocalDateTime.now());
                }
                noteDao.insertNote(note);
                finish();
            }
        });
    }

}