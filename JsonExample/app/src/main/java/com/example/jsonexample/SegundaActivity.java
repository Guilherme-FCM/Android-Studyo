package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jsonexample.databinding.ActivitySegundaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SegundaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ActivitySegundaBinding binding;
    private List<Estudante> lista;
    private String dados;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySegundaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dados = getIntent().getStringExtra("dados");
        lista = consumirJson();
        adapter = new ArrayAdapter<Estudante>(this, android.R.layout.simple_list_item_1, lista);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(this);
    }

    private List<Estudante> consumirJson() {
        List<Estudante> estudantes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dados);
            JSONArray jsonArray = jsonObject.getJSONArray("estudantes");
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Estudante estudante = new Estudante(
                        object.getString("nome"),
                        object.getString("disciplina"),
                        object.getInt("nota")
                );
                estudantes.add(estudante);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return estudantes;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog alertDialog = new AlertDialog.Builder(SegundaActivity.this).create();
        alertDialog.setTitle("Dados do Estudante");
        alertDialog.setMessage("Nome: "+lista.get(i).getNome()+
                "\nDisciplina: "+lista.get(i).getDisciplina()+
                "\nNota: "+lista.get(i).getNota());
        alertDialog.show();
        alertDialog.setCancelable(true);
    }
}