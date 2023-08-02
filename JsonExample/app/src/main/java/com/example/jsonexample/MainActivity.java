package com.example.jsonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jsonexample.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private List<Estudante> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lista = new ArrayList<>();
    }

    public void criarLista(View v) {
        lista.add(
                new Estudante(
                        binding.nome.getText().toString(),
                        binding.disciplina.getText().toString(),
                        Integer.parseInt(binding.nota.getText().toString())
                )
        );
        Toast.makeText(this, "Item inserido com sucesso!", Toast.LENGTH_SHORT).show();
    }

//    private String criarJson() {
//        JSONArray jsonArray = new JSONArray();
//        for(int i=0;i<lista.size();i++){
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("nome",lista.get(i).getNome());
//                jsonObject.put("disciplina",lista.get(i).getDisciplina());
//                jsonObject.put("nota",lista.get(i).getNota());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            jsonArray.put(jsonObject);
//        }//for
//        return "{estudantes:"+jsonArray.toString()+"}";
//    }

    private String criarJson(List<Estudante> estudantes) {
        Gson gson = new Gson();
        return gson.toJson(estudantes);
    }

    public void gerarJson(View v) {
        binding.resultado.setText(criarJson(lista));
    }

    public void abrirTela2(View v) {
        Intent it = new Intent(this, SegundaActivity.class);
        it.putExtra("dados", criarJson(lista));
        startActivity(it);
    }
}