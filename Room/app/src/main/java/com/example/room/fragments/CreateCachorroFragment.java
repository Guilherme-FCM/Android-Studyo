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

    public CreateCachorroFragment(CachorroDao dao) {
        this.dao = dao;
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

        button.setOnClickListener((v -> {
            dao.insert(
                    new Cachorro(
                            nome.getText().toString(),
                            raca.getText().toString(),
                            Integer.parseInt(pessoaId.getText().toString())
                    )
            );
            startActivity( new Intent(getActivity(), MainActivity.class) );
        }));
        return view;
    }
}