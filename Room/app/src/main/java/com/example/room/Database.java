package com.example.room;

import androidx.room.RoomDatabase;

import com.example.room.daos.CachorroDao;
import com.example.room.daos.PessoaDao;
import com.example.room.entities.Cachorro;
import com.example.room.entities.Pessoa;

@androidx.room.Database(entities = {Pessoa.class, Cachorro.class}, version = 1)
public abstract class Database extends RoomDatabase {
    abstract PessoaDao pessoaDao();
    abstract CachorroDao cachorroDao();
}
