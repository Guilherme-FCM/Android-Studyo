package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> linguagens;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        linguagens = new ArrayList<>(
                Arrays.asList(getResources().getStringArray(R.array.linguagens))
        );
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, linguagens);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            openDialogFragment(i);
        });
    }

    private void openDialogFragment(int i) {
        MyDialogFragment dialog = new MyDialogFragment(linguagens, i, adapter);
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}