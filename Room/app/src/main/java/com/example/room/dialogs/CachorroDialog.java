package com.example.room.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.room.entities.Cachorro;

import java.util.ArrayList;

public class CachorroDialog extends DialogFragment {
    private ArrayAdapter adapter;
    private Cachorro cachorro;


    public CachorroDialog(Cachorro cachorro, ArrayAdapter adapter) {
        this.adapter = adapter;
        this.cachorro = cachorro;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Você deseja realmente excluir '"+this.getItem()+"'?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    String removed = this.removeItem();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Linguagem " + removed + " removida da lista", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                    Toast.makeText(getContext(), "Operação de remoção cancelada", Toast.LENGTH_SHORT).show();
                });
        return builder.create();
    }

    private String getItem() {
        return "this.linguagens.get(this.item)";
    }
    private String removeItem() {
        return "this.linguagens.remove(this.item)";
    }
}
