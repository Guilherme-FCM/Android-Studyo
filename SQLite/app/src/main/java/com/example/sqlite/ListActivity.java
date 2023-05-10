package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;
    private ArrayAdapter<Pessoa> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lista = findViewById(R.id.listView);
        ArrayList<Pessoa> dados =(ArrayList<Pessoa>) getIntent().getSerializableExtra("dados");
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dados);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Pessoa p = (Pessoa) adapterView.getItemAtPosition(i);
        Intent it = new Intent(ListActivity.this,MainActivity.class);
        it.putExtra("dado",p);
        startActivity(it);
    }


}