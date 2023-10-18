package com.example.handlespostmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler;
    private int numberG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonClicar);
        textView = findViewById(R.id.textViewNumber);

        button.setOnClickListener(v -> {
            Log.i("Thread1", Thread.currentThread().getName());
            Random random = new Random();
            handler = new Handler(Looper.getMainLooper());

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Log.i("Thread2", Thread.currentThread().getName());
                    numberG = random.nextInt();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    handler.post(() -> {
                       Log.i("Thread3", Thread.currentThread().getName());
                       textView.setText(String.valueOf(numberG));
                    });
                }
            }).start();
        });
    }
}