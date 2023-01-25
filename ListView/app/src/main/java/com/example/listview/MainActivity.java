package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView listView;
    private ArrayList<String> data;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        fillData();
        fillArrayAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    private void fillArrayAdapter() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
    }

    private void fillData() {
        data = new ArrayList<>();
        data.add("Java");
        data.add("C");
        data.add("PHP");
        data.add("Javascript");
    }

    private void showActivity(String item) {
        Intent it = null;
        switch (item) {
            case "Java":
                it = new Intent(this, ActivityJava.class);
                startActivity(it);
                break;
            case "C":
                it = new Intent(this, ActivityC.class);
                startActivity(it);
                break;
            case "PHP":
                it = new Intent(this, ActivityPhp.class);
                startActivity(it);
                break;
            case "Javascript":
                it = new Intent(this, ActivityJavascript.class);
                startActivity(it);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        showActivity(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, "Você pressionou: " + item, Toast.LENGTH_SHORT).show();
        return true;
    }
}