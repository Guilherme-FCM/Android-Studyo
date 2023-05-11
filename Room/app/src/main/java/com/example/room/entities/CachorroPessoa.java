package com.example.room.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CachorroPessoa {
    @Embedded
    public Cachorro cachorro;

    @Relation(
            entity = Pessoa.class,
            parentColumn = "pessoa_id",
            entityColumn = "id"
    )
    public List<Pessoa> pessoas;
}
