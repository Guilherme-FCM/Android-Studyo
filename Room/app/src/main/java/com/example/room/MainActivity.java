package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.room.entities.Cachorro;
import com.example.room.entities.CachorroPessoa;
import com.example.room.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Database db;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // É necessário utilizar uma Thread secundária -> allowMainThreadQueries = gambiarra para burlar
        db = Room.databaseBuilder(this, Database.class, "banco_room").allowMainThreadQueries().build();

        insert();
        textView.setOnClickListener((view -> {
            list();
        }));
    }

    private void list() {
        List<CachorroPessoa> result = db.cachorroDao().listWithPessoa();
        StringBuilder stringBuilder = new StringBuilder();

        for (CachorroPessoa cp : result) {
            stringBuilder
                    .append("Cachorro: " + cp.cachorro.toString())
                    .append("Pessoa: " + cp.pessoas.toString());
        }
        textView.setText(stringBuilder.toString());
    }

    private void insert() {
        long p1 = db.pessoaDao().insert( new Pessoa("Pessoa1", "999999999") );
        long p2 = db.pessoaDao().insert( new Pessoa("Pessoa2", "888888888") );
        long p3 = db.pessoaDao().insert( new Pessoa("Pessoa3", "777777777") );

        db.cachorroDao().insert( new Cachorro("Cachorro1", "Raça2", 1) );
        db.cachorroDao().insert( new Cachorro("Cachorro2", "Raça3", 2) );
        db.cachorroDao().insert( new Cachorro("Cachorro3", "Raça1", 3) );
    }
}