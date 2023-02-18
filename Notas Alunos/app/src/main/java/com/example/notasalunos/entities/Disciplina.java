package com.example.notasalunos.entities;

import java.io.Serializable;

public class Disciplina implements Serializable {
    private String nome;
    private Double nota;

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
