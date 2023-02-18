package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.notasalunos.entities.Aluno;
import com.example.notasalunos.entities.Disciplina;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editText;
    private Spinner spinner;
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        spinner = findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this,R.array.disciplinas, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Intent it = getIntent();
        ArrayList<Aluno> alunos = (ArrayList<Aluno>) it.getSerializableExtra("alunos");
        if (alunos != null && alunos.size() > 0) this.alunos = alunos;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!editText.getText().toString().isEmpty()) {
            Disciplina disciplina = new Disciplina( (String) adapterView.getItemAtPosition(i) );
            Aluno aluno = new Aluno( editText.getText().toString(), disciplina );

            Intent it = new Intent(MainActivity.this, MainActivity2.class);
            it.putExtra("aluno", aluno);
            it.putExtra("alunos", alunos);
            startActivity(it);
        };
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}