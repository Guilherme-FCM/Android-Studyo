package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BancoHelper extends SQLiteOpenHelper {
    private static final int BANCO_VERSAO = 1;
    private static final String BANCO_NOME = "banco_sqlite";
    private static final String BANCO_TABELA = "contato";
    private static final String ID = "_id";
    private static final String NOME = "nome";
    private static final String TELEFONE = "telefone";

    private static final String CRIA_TABELA = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
            BANCO_TABELA, ID, NOME, TELEFONE
    );

    private static final String DELETE_TABELA = String.format("DROP TABLE IF EXISTS %s", BANCO_TABELA);

    public BancoHelper(@Nullable Context context) {
        super(context, BANCO_NOME, null, BANCO_VERSAO);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CRIA_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int o, int n) {
        sqLiteDatabase.execSQL(DELETE_TABELA);
        onCreate(sqLiteDatabase);
    }

    public long adicionarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, pessoa.getNome());
        values.put(TELEFONE, pessoa.getTelefone());

        long id = db.insert(BANCO_TABELA, null, values);
        db.close();
        return id;
    }

    public ArrayList<Pessoa> listarPessoa() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        String query = String.format("SELECT * FROM %s", BANCO_TABELA);
        Cursor cursor = db.rawQuery(query, null);
//        db.query(BANCO_NOME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(0));
            String nome = cursor.getString(1);
            String telefone = cursor.getString(2);
            pessoas.add( new Pessoa(id, nome, telefone) );
        }
        db.close();
        return pessoas;
    }

    public int atualizarPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOME, pessoa.getNome());
        values.put(TELEFONE, pessoa.getTelefone());

        String where = ID + " =?";
        String[] args = {String.valueOf(pessoa.getId())};

        int result = db.update(BANCO_TABELA, values, where, args);
        db.close();
        return result;
    }

    public int excluirPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = ID + " =?";
        String[] args = {String.valueOf(pessoa.getId())};

        int result = db.delete(BANCO_TABELA, where, args);
        db.close();
        return result;
    }
}
