package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.notasalunos.entities.Aluno;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;
    private Handler handler;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);

        Intent it = getIntent();
        ArrayList<Aluno> alunos = (ArrayList<Aluno>) it.getSerializableExtra("alunos");

        handler = new Handler();
        i = progressBar.getProgress();
        new Thread(() -> {
            while (i < 100) {
                i += 2;
                handler.post(() -> {
                    progressBar.setProgress(i);
                    textView.setText(i + "/" + progressBar.getMax());
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            intent.putExtra("alunos", alunos);
            startActivity(intent);
        }).start();
    }
}