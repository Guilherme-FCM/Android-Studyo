package com.example.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.room.entities.Cachorro;
import com.example.room.entities.CachorroPessoa;

import java.util.List;

@Dao
public interface CachorroDao {
    @Insert
    Long insert(Cachorro cachorro);

    @Query("SELECT * FROM Cachorro")
    List<Cachorro> list();

    @Transaction
    @Query("SELECT * FROM Cachorro")
    List<CachorroPessoa> listWithPessoa();
}
