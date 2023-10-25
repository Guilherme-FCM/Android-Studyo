package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView lista;
    private ArrayAdapter<Contato> dadosAdapter;
    private List<Contato> dados_contatos;
    private StringBuilder dados;
    private static final int PERMISSIONS_READ = 100;
    private static final String PERMISSIONS_REQUEST = Manifest.permission.READ_CONTACTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.textViewDados);
        dados_contatos = new ArrayList<>();
        dados = new StringBuilder();

        lista.setOnClickListener(v -> {
            obterDados();
            lista.setText(dados_contatos.toString());
        });
    }

    private void obterDados() {
        Uri uri = null;
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            checkSelfPermission(PERMISSIONS_REQUEST) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[]{PERMISSIONS_REQUEST}, PERMISSIONS_READ);
            uri = ContactsContract.CommonDataKinds.Contactables.CONTENT_URI;
        } else {
            uri = ContactsContract.Data.CONTENT_URI;
        }

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        while (cursor.moveToNext()) {
            int id_contato = cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID);
            int id = cursor.getInt(id_contato);

            int id_nome = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String nome = cursor.getString(id_nome);

            int id_data = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DATA);
            String data = cursor.getString(id_data);

            dados_contatos.add( new Contato(id, nome, data) );
        }

        cursor.close();
        Log.i("dados", dados_contatos.toString());
    }
}