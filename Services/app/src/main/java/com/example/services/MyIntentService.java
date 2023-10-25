package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("Servico 1");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("thread2", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            Log.i("laco", String.valueOf(i));
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "criando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "destruindo...", Toast.LENGTH_SHORT).show();
    }
}
