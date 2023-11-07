package com.example.contentproviderconsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<User> dadosAdapter;
    private List<User> dadosUser;
    private TextView lista;
    private static final int PERMISSION_READ = 100;
    private static final String PERMISSION_REQUEST = Manifest.permission.READ_CONTACTS;
    private StringBuilder dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.text);
        dadosUser = new ArrayList<>();
        dados = new StringBuilder();
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dados = obterDados();
                lista.setText(dados);
            }

            @SuppressLint("Range")
            private String obterDados() {
                Uri uri = null;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(PERMISSION_REQUEST) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{PERMISSION_REQUEST}, PERMISSION_READ);
                    uri = Uri.parse("content://com.demo.user.provider/users");
                }else{
                    uri = Uri.parse("content://com.demo.user.provider/users");
                }
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                if(cursor.moveToFirst()) {
                    StringBuilder strBuild=new StringBuilder();
                    while (!cursor.isAfterLast()) {
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        dadosUser.add(new User(Integer.parseInt(id), name));
                        cursor.moveToNext();
                    }

                }
                else {
                    return  "No Records Found";
                }
                cursor.close();
                for (User c: dadosUser){
                    dados.append(c);
                    dados.append("\n");
                }
                return  dados.toString();
            }
        });
    }
}