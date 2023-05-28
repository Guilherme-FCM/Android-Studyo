package com.example.recyclecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CardAdapter(MainActivity.this, getData());
        recyclerView.setLayoutManager(
            new LinearLayoutManager(MainActivity.this)
        );
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, adapter.getItem(position).getTitulo(), Toast.LENGTH_SHORT).show();
    }

    private ArrayList<CardModel> getData() {
        ArrayList<CardModel> data = new ArrayList<>();
        data.add( new CardModel(R.drawable.poster_vingadores_4_ultimato, "Vingadores") );
        data.add( new CardModel(R.drawable.kawaki, "Ultimato") );
        data.add( new CardModel(R.drawable.poster_vingadores_4_ultimato, "Kawaki") );
        data.add( new CardModel(R.drawable.kawaki, "Boruto") );
        return data;
    }
}