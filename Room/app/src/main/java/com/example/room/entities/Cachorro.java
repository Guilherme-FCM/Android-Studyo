package com.example.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cachorro {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String raca;

    @ColumnInfo(name = "pessoa_id")
    private int pessoaId;

    public Cachorro() {}

    @Ignore
    public Cachorro(String nome, String raca, int pessoaId) {
        this.nome = nome;
        this.raca = raca;
        this.pessoaId = pessoaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Override
    public String toString() {
        return "Cachorro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", pessoaId=" + pessoaId +
                '}';
    }
}
