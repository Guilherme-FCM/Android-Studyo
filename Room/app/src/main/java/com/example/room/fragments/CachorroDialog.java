package com.example.room.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.room.MainActivity;
import com.example.room.daos.CachorroDao;
import com.example.room.entities.Cachorro;

public class CachorroDialog extends DialogFragment {
    private ArrayAdapter adapter;
    private Cachorro cachorro;
    private CachorroDao dao;


    public CachorroDialog(Cachorro cachorro, CachorroDao dao, ArrayAdapter adapter) {
        this.adapter = adapter;
        this.cachorro = cachorro;
        this.dao = dao;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("O que deseja realizar com o(a) '" + cachorro.getNome() + "'?")
                .setPositiveButton("Atualizar", (dialogInterface, i) -> {
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Atualizar", Toast.LENGTH_SHORT).show();
                })
                .setNeutralButton("Excluir", (dialogInterface, i) -> {
                    dao.delete(cachorro);
                    startActivity( new Intent(getActivity(), MainActivity.class) );
                    Toast.makeText(getContext(), cachorro.getNome() + " excluído", Toast.LENGTH_SHORT).show();
                });
        return builder.create();
    }
}
