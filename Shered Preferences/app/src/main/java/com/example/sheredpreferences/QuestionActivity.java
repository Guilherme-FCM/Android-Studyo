package com.example.sheredpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity{
    private String answer1;
    private String answer2;
    private String answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        RadioGroup question1 = findViewById(R.id.question1);
        RadioGroup question2 = findViewById(R.id.question2);
        RadioGroup question3 = findViewById(R.id.question3);
        question1.setOnCheckedChangeListener(this::onCheckQuestion1);
        question2.setOnCheckedChangeListener(this::onCheckQuestion2);
        question3.setOnCheckedChangeListener(this::onCheckQuestion3);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((view -> {
            int score = 0;
            if (answer1.equals("Java")) score += 3;
            if (answer2.equals("Activity")) score += 3;
            if (answer3.equals("SQLite")) score += 4;

            boolean committed = recordScore(score);
            if (committed) startActivity(new Intent(this, FinalActivity.class));
            else Toast.makeText(this, "Deu erro!", Toast.LENGTH_SHORT).show();
        }));
    }


    public boolean recordScore(int score) {
        SharedPreferences sp = getSharedPreferences("dados", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("score", score);
        return editor.commit();
    }

    public void onCheckQuestion1(RadioGroup radioGroup, int i) {
        RadioButton selected = findViewById(i);
        answer1 = selected.getText().toString();
    }
    public void onCheckQuestion2(RadioGroup radioGroup, int i) {
        RadioButton selected = findViewById(i);
        answer2 = selected.getText().toString();
    }
    public void onCheckQuestion3(RadioGroup radioGroup, int i) {
        RadioButton selected = findViewById(i);
        answer3 = selected.getText().toString();
    }
}