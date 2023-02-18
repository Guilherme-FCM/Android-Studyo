package com.example.notasalunos.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notasalunos.entities.Aluno;

import java.util.ArrayList;

public class RemoveAlunoDialog extends DialogFragment {

    private Aluno aluno;
    private ArrayList<Aluno> alunos;
    private ArrayAdapter<Aluno> adapter;

    public RemoveAlunoDialog(Aluno aluno, ArrayList<Aluno> alunos, ArrayAdapter<Aluno> adapter) {
        this.aluno = aluno;
        this.alunos = alunos;
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Deseja escluir este item?")
                .setPositiveButton("Sim", (DialogInterface dialogInterface, int i) -> {
                        alunos.remove(aluno);
                        adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Não", (DialogInterface dialogInterface, int i) -> {

                });
        return builder.create();
    }
}

