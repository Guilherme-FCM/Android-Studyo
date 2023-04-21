package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView helloView;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        helloView = findViewById(R.id.helloView);
        resultView = findViewById(R.id.resultView);

        String name = getIntent().getStringExtra("name");
        double imc = getIntent().getDoubleExtra("result", 0);

        String message = "";
        if (imc <= 18.5) message = "Abaixo do peso";
        else if (imc > 18.5 && imc <= 24.9) message = "com Peso normal";
        else if (imc > 24.9 && imc <= 29.9) message = "com Sobrepeso";
        else if (imc > 29.9 && imc <= 34.9) message = "com Obesidade grau 1";
        else if (imc > 34.9) message = "com Obesidade grau 3";

        helloView.setText("Olá " + name + ".");
        resultView.setText("Você está " + message + ".");
    }
}