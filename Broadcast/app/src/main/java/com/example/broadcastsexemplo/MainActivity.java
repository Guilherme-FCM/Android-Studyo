package com.example.broadcastsexemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnGerar;
    private ListView listView;
    private ArrayAdapter<Integer> adapter;
    private ArrayList<Integer> dados;
    private int numeroAleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGerar = findViewById(R.id.buttonGerar);
        listView = findViewById(R.id.listarNumeros);
        dados = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listView.setAdapter(adapter);

        btnGerar.setOnClickListener(v -> {
           gerar();
        });
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastsexemplo.MY_INTENT");
        registerReceiver(myBroadcastReceiver, intentFilter);
    }
    private void gerar() {
        Random random = new Random();
        numeroAleatorio = random.nextInt(10);
        Toast.makeText(this, "Número gerado" + numeroAleatorio, Toast.LENGTH_SHORT).show();
        dispararMensagem();
    }
    private void dispararMensagem() {
        Intent intent = new Intent();
        intent.putExtra("numero", numeroAleatorio);
        intent.setAction("com.example.broadcastsexemplo.MY_INTENT");
        sendBroadcast(intent);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int num = intent.getIntExtra("numero", 0);
            dados.add(num);
            adapter.notifyDataSetChanged();
        }
    }

}