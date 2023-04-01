package com.example.sheredpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        layout = findViewById(R.id.finalActivity);
        TextView textView =findViewById(R.id.textView) ;

        SharedPreferences sp = getSharedPreferences("dados", MODE_PRIVATE);
        int score = sp.getInt("score", 0);
        textView.setText("O seu score é de " + score + " pontos");
    }
}