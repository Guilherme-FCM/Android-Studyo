package com.example.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.room.entities.Pessoa;

import java.util.List;

@Dao
public interface PessoaDao {
    @Insert
    Long insert(Pessoa pessoa);

    @Query("SELECT * FROM Pessoa")
    List<Pessoa> list();
}
