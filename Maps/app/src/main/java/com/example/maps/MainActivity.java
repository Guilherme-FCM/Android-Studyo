package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setOnClickListener((v) -> {
            Intent it = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(it);
        });
    }
}