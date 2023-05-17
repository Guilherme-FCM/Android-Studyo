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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.room.MainActivity;
import com.example.room.R;
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
                    FragmentManager fragManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragManager.beginTransaction();
                    fragTransaction.replace(R.id.frame, new CreateCachorroFragment(dao, cachorro));
                    fragTransaction.commit();
                })
                .setNeutralButton("Excluir", (dialogInterface, i) -> {
                    dao.delete(cachorro);
                    startActivity( new Intent(getActivity(), MainActivity.class) );
                    Toast.makeText(getContext(), cachorro.getNome() + " excluído", Toast.LENGTH_SHORT).show();
                });
        return builder.create();
    }
}
