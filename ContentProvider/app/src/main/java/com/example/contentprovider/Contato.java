package com.example.contentprovider;

public class Contato {
    public int id;
    public String nome;
    public String data;

    public Contato(int id, String nome, String data) {
        this.id = id;
        this.nome = nome;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
