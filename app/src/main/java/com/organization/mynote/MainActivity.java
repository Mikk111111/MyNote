package com.organization.mynote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> notesList = new ArrayList<>();

        notesList.addAll(Arrays.asList("Pirm","Antr","Trec","Ketivi","Penk","Sest","Sek"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                notesList
        );
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}