package com.example.caracoroua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int coin = intent.getIntExtra("coin", -1);
        int random = new Random().nextInt(2);
        Toast.makeText(this, "Selecionado: "+coin, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Resultado:"+random, Toast.LENGTH_SHORT).show();

        TextView randomView = findViewById(R.id.random);
        TextView resultView = findViewById(R.id.result);

        randomView.setText(random == 0 ? "Deu Cara" : "Deu Coroa");
        resultView.setText(coin == random ? "Você Ganhou!" : "Você Perdeu =(");

    }
}