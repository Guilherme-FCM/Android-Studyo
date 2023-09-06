package com.example.latlngbounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewHelloWorld = findViewById(R.id.textViewHelloWorld);
        textViewHelloWorld.setOnClickListener((view) -> {
            Intent it = new Intent(this, MapsActivity.class);
            startActivity(it);
        });
    }
}
