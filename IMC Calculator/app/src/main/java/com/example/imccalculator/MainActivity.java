package com.example.imccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText weightText;
    private EditText heightText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.name);
        weightText = findViewById(R.id.weight);
        heightText = findViewById(R.id.height);
        calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = nameText.getText().toString();
                    Double weight = Double.valueOf(weightText.getText().toString());
                    Double height = Double.valueOf(heightText.getText().toString());
                    double imc = weight / (height * height);
                    Toast.makeText(MainActivity.this, "Imc = "+imc, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("result", imc);
                    intent.putExtra("name", name);
                    startActivity(intent);
                 } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "As informações inseridas são inválidas, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}