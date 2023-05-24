package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jokenpo.databinding.ActivitySelectBinding;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity /* implements CardAdapter.ItemClickListener */ {
    ActivitySelectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        CardAdapter adapter = new CardAdapter(this, getData());
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        binding.recyclerView.setAdapter(adapter);
//        adapter.setClickListener(this)
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, adapter.getItem(position).getTitulo(), Toast.LENGTH_SHORT).show();
//    }

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
}