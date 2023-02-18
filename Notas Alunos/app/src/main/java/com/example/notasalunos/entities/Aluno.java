package com.example.notasalunos.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private String nome;
    private Disciplina disciplina;

    public Aluno(String nome, Disciplina disciplina) {
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public boolean isAprovado(){
        return this.getDisciplina().getNota() >= 6;
    }

    @NonNull
    @Override
    public String toString() {
        return
            "Aluno: " + this.getNome() + "\n" +
            "Disciplina: " + this.getDisciplina().getNome() + "\n" +
            "Nota: " + this.getDisciplina().getNota() + "\n" +
            "Status: " + (this.isAprovado() ? "Aprovado" : "Reprovado");
    }
}