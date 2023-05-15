package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.dialogs.CachorroDialog;
import com.example.room.entities.Cachorro;
import com.example.room.entities.CachorroPessoa;
import com.example.room.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // É necessário utilizar uma Thread secundária -> allowMainThreadQueries = gambiarra para burlar
        db = Room.databaseBuilder(this, Database.class, "banco_room").allowMainThreadQueries().build();

        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listCachorro());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            CachorroDialog dialog = new CachorroDialog(getCachorro(i), adapter);
            dialog.show(getSupportFragmentManager(), "CachorroDialog");
        });
    }

    private List<String> listCachorroPessoa() {
        List<CachorroPessoa> result = db.cachorroDao().listWithPessoa();
        List<String> strings = new ArrayList<>();

        for (CachorroPessoa cp : result) {
            strings.add(
                    "Cachorro: " + cp.cachorro.toString() +
                    "Pessoa: " + cp.pessoas.toString()
            );
        }
        return strings;
    }

    private String[] listCachorro() {
        return db.cachorroDao().list()
                .stream()
                .map(Cachorro::toString)
                .toArray(String[]::new);
    }

    private Cachorro getCachorro(int i) {
        return db.cachorroDao().list().get(i);
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