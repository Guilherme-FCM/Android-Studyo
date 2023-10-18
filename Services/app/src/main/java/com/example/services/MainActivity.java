package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button buttonStart,buttonStop;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);

//        it = new Intent(this, MyIntentService.class);
        it = new Intent(this, MyService.class);

        buttonStart.setOnClickListener(v -> {
            Log.e("thread1",Thread.currentThread().getName());
            startService(it);
        });

        buttonStop.setOnClickListener(v -> {
            stopService(it);
        });
    }
}