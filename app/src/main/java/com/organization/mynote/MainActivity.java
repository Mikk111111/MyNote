package com.organization.mynote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Note> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Note> notesList = generateNoteList(20);

        setUpView();
        setUpListView(notesList);
    }

    private List<Note> generateNoteList(int record) {
        List<Note> notesList = new ArrayList<>();
        for (int i = 1;i<=record;i++){
            Note note = new Note(1,"name_"+i,"Some note text_"+i);
            notesList.add(note);
        }
        return notesList;
    }

    private void setUpListView(List<Note> notesList) {
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                notesList
        );
        listView.setAdapter(adapter);
    }

    private void setUpView() {
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.listView);

    }
}