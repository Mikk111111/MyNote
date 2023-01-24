package com.organization.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoteDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        String message = getIntent().getStringExtra(Constant.KEY_NOTE_DETAILS);

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}