package com.example.room.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.room.MainActivity;
import com.example.room.R;
import com.example.room.daos.CachorroDao;
import com.example.room.entities.Cachorro;

public class CreateCachorroFragment extends Fragment {
    private CachorroDao dao;
    private Cachorro cachorro;

    public CreateCachorroFragment(CachorroDao dao) {
        this.dao = dao;
    }

    public CreateCachorroFragment(CachorroDao dao, Cachorro cachorro) {
        this.dao = dao;
        this.cachorro = cachorro;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_cachorro, container, false);

        EditText nome = view.findViewById(R.id.nome);
        EditText raca = view.findViewById(R.id.raca);
        EditText pessoaId = view.findViewById(R.id.pessoa);
        Button button = view.findViewById(R.id.button);

        if (cachorro != null) {
            nome.setText(cachorro.getNome());
            raca.setText(cachorro.getRaca());
            pessoaId.setText(String.valueOf(cachorro.getPessoaId()));
        }

        button.setOnClickListener((v -> {
            if (cachorro != null){
                cachorro.setNome(nome.getText().toString());
                cachorro.setRaca(raca.getText().toString());
                cachorro.setPessoaId(Integer.parseInt(pessoaId.getText().toString()));
                dao.update(cachorro);
            } else {
                Cachorro cachorro = new Cachorro(
                    nome.getText().toString(),
                    raca.getText().toString(),
                    Integer.parseInt(pessoaId.getText().toString())
                );
                dao.insert(cachorro);
            }
            startActivity( new Intent(getActivity(), MainActivity.class) );
        }));
        return view;
    }
}