package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        resultView = findViewById(R.id.resultView);
        Intent i = getIntent();
        double message = i.getDoubleExtra("result", 0);
        resultView.setText("Resultado = " + message);
    }

}