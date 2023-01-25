package com.organization.mynote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.organization.mynote.repository.MainDatabase;
import com.organization.mynote.repository.NoteDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Note> adapter;
    private List<Note> notesList;
    private NoteDao noteDao;

    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        notesList = generateNoteList(20);

        noteDao= MainDatabase.getRoomInstance(getApplicationContext()).noteDao();
//        noteDao.insertNotes(notesList);
        notesList=noteDao.getAll();

        setUpView();

        setUpClickOnListItem();
        setUpLongClickListItem();
        setUpClickOnFab();

    }

    private List<Note> generateNoteList(int record) {

        List<Note> notesList = new ArrayList<>();

        for (int i = 1;i<=record;i++){
            Note note = new Note(i,"name_"+i,"Some note text_"+i);
            notesList.add(note);
        }
        return notesList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.notesList = noteDao.getAll();
        setUpListViewAdapter();

    }

    private void setUpView() {
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.listView);
        this.fab = findViewById(R.id.fab);
    }

    private void setUpListViewAdapter() {
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                notesList
        );
        listView.setAdapter(adapter);
    }

    private void setUpClickOnListItem() {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Log.i(TAG, "onItemClick: "+position+" # "+ notesList.get(position));
                Intent intent = new Intent(MainActivity.this,NoteDetails.class);
                Note note = MainActivity.this.notesList.get(position);
                intent.putExtra(Constant.KEY_NOTE_ID,note.getId());
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    private void setUpLongClickListItem() {
        AdapterView.OnItemLongClickListener longListener = (adapterView, view, position, l) -> {
            //Log.i(TAG, "onLongItemClick: "+position+" # "+ notesList.get(position));
            displayNoteDeleteAlert(position);
            return true;//determines if short click will react
        };
        listView.setOnItemLongClickListener(longListener);
    }

    private void setUpClickOnFab() {
        fab.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,NoteDetails.class));
        });
    }

    private void displayNoteDeleteAlert(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Log.i(TAG, "Deleted: \n"+ notesList.get(position));
                displayMessage("Remove note (id: "+notesList.get(position).getId()+")");
                noteDao.deleteNote(notesList.get(position));
                notesList.remove(position);
                adapter.notifyDataSetChanged();
            }
        };
        builder.setMessage("U Sure?")
                .setPositiveButton("Yes",listener)
                .setNegativeButton("No",null)
                .show();
    }

    private void displayMessage(String message) {
        Snackbar.make(listView,message,Snackbar.LENGTH_LONG).show();
    }

}