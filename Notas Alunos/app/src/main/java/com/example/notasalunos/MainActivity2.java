package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notasalunos.entities.Aluno;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
    private ArrayList<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        Intent it = getIntent();
        alunos = (ArrayList<Aluno>) it.getSerializableExtra("alunos");
        Aluno aluno = (Aluno) it.getSerializableExtra("aluno");
        textView.setText("Insira a nota do(a) aluno(a) " + aluno.getNome() + " em " + aluno.getDisciplina().getNome());

        button.setOnClickListener((View view) -> {
            String nota = editText.getText().toString();
            aluno.getDisciplina().setNota( Double.parseDouble(nota) );
            alunos.add(aluno);

            Intent intent = alunos.size() >= 3
                ? new Intent(MainActivity2.this, MainActivity3.class)
                : new Intent(MainActivity2.this, MainActivity.class);

            intent.putExtra("alunos", alunos);
            startActivity(intent);
        });

    }
}