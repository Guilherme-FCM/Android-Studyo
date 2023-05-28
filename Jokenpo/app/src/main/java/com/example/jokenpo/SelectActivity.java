package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jokenpo.databinding.ActivityMainBinding;
import com.example.jokenpo.databinding.ActivitySelectBinding;

import java.util.ArrayList;
import java.util.Random;

public class SelectActivity extends AppCompatActivity implements CardAdapter.ItemClickListener {
    ActivitySelectBinding binding;
    private CardAdapter adapter;
    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sp = getSharedPreferences("jokenpo", MODE_PRIVATE);
        String username = sp.getString("username", "");
        binding.username.setText(username + ", selecione sua jogada");

        adapter = new CardAdapter(this, getData());
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(SelectActivity.this)
        );
        binding.recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        binding.play.setOnClickListener(this::startPlay);
        binding.random.setOnClickListener(this::randomSelect);
    }

    @Override
    public void onItemClick(View view, int position) {
        // Deixar todos os CardView's com fundo padrão

        selected = position;
        view.setBackgroundColor(getResources().getColor(R.color.selected));
    }

    public ArrayList<Card> getData() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] cardNames = getResources().getStringArray(R.array.jokenpoNames);
        TypedArray cardDrawables = getResources().obtainTypedArray(R.array.jokenpoDrawables);

        for (int i = 0; i < cardNames.length; i++) {
            Card card = new Card(cardDrawables.getResourceId(i, 0), cardNames[i]);
            cards.add(card);
        }
        return cards;
    }

    public void startPlay(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("selected", selected);
        startActivity(intent);
    }

    public void randomSelect(View view) {
        Random random = new Random();
        selected = random.nextInt(3);
        startPlay(view);
    }
}