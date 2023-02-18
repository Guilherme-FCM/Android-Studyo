package com.example.notasalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.notasalunos.dialogs.RemoveAlunoDialog;
import com.example.notasalunos.entities.Aluno;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Aluno> alunos;
    private ArrayAdapter<Aluno> adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listView = findViewById(R.id.listview);

        Intent it = getIntent();
        alunos = (ArrayList<Aluno>) it.getSerializableExtra("alunos");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Aluno aluno = (Aluno) adapterView.getAdapter().getItem(i);
        RemoveAlunoDialog dialog = new RemoveAlunoDialog(aluno, alunos, adapter);
        dialog.show(getSupportFragmentManager(),null);
    }
}