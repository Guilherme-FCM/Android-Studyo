package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText input1;
    private EditText input2;

    private Button sumButton;
    private Button minusButton;
    private Button multiplicationButton;
    private Button divisionButton;
    private Button calcButton;
    private String selectedOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        sumButton = findViewById(R.id.sumButton);
        minusButton = findViewById(R.id.minusButton);
        multiplicationButton = findViewById(R.id.multiplicationButton);
        divisionButton = findViewById(R.id.divisionButton);
        calcButton = findViewById(R.id.calcButton);

        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                view.setBackgroundColor(R.color.purple_700);
                Toast.makeText(MainActivity.this, "Selecionado a operação de SOMA", Toast.LENGTH_SHORT).show();
                selectedOperation = "+";
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Selecionado a operação de SUBTRAÇÂO", Toast.LENGTH_SHORT).show();
                selectedOperation = "-";
            }
        });
        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Selecionado a operação de MULTIPLICAÇÂO", Toast.LENGTH_SHORT).show();
                selectedOperation = "*";
            }
        });
        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Selecionado a operação de DIVISÃO", Toast.LENGTH_SHORT).show();
                selectedOperation = "/";
            }
        });

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Double value1 = Double.valueOf(input1.getText().toString());
                    Double value2 = Double.valueOf(input2.getText().toString());
                    Intent intent = new Intent(
                            MainActivity.this,
                            Activity2.class
                    );
                    double result = 0;
                    switch (selectedOperation) {
                        case "+":
                            result = value1 + value2;
                            break;
                        case "-":
                            result = value1 - value2;
                            break;
                        case "*":
                            result = value1 * value2;
                            break;
                        case "/":
                            result = value1 / value2;
                            break;
                    }
                    intent.putExtra("result", result);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "As informações inseridas são inválidas, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
