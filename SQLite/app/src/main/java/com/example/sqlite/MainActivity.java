package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pessoa = (Pessoa) getIntent().getSerializableExtra("dado");
        if(pessoa != null){
            binding.nome.setText(pessoa.getNome());
            binding.telefone.setText(pessoa.getTelefone());
        }

        binding.insert.setOnClickListener(this::save);
        binding.select.setOnClickListener(this::list);
        binding.delete.setOnClickListener(this::delete);
    }

    public void save(View view) {
        BancoHelper bh = new BancoHelper(getApplicationContext());

        if (pessoa == null) {
            pessoa = new Pessoa();
            pessoa.setNome(binding.nome.getText().toString());
            pessoa.setTelefone(binding.telefone.getText().toString());

            long id = bh.adicionarPessoa(pessoa);
            if (id != -1) {
                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
                pessoa = null;
                binding.nome.setText("");
                binding.telefone.setText("");
            } else {
                Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
            }
        } else {
            pessoa.setNome(binding.nome.getText().toString());
            pessoa.setTelefone(binding.telefone.getText().toString());

            int result = bh.atualizarPessoa(pessoa);
            if (result != 0) {
                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
            }
            binding.nome.setText("");
            binding.telefone.setText("");
        }
    }

    public void list(View view) {
        BancoHelper bh = new BancoHelper(getApplicationContext());
        ArrayList<Pessoa> dados = bh.listarPessoa();
        if(dados!=null){
            Intent i = new Intent(MainActivity.this,ListActivity.class);
            i.putExtra("dados",dados);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(MainActivity.this,"sem dados",Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(View view) {
        BancoHelper bh = new BancoHelper(getApplicationContext());
        int result = bh.excluirPessoa(pessoa);
        if (result > 0) {
            Toast.makeText(this, "Excluído!", Toast.LENGTH_SHORT).show();
            binding.nome.setText("");
            binding.telefone.setText("");
        } else {
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }
}