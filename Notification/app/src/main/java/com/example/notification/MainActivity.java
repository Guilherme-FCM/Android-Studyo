package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CANAL_ID = "1";
    private static final int NOTIFICACAO_ID = 2;
    private static final String PERMISSAO = Manifest.permission.POST_NOTIFICATIONS;
    private Button button;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;
    private Handler handler;
    private final int max = 100;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            criarCanalNotificacao();

        gerar();

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            notificar();
        });
    }

    // Varsão 3
    private void gerar() {
        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
                .Builder(this, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My title")
                .setContentText("Message...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setProgress(max, 0, false);

        handler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            while (i < max) {
                i += 1;
                handler.post(() -> {
                   builder.setProgress(max, i, false);
                   if (i >= max) {
                       builder.setContentText("Download completo")
                               .setProgress(0, 0, false);
                   }
                   notificar();
                });

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    // Versão 2
    /* private void gerar() {
        Intent i = new Intent(this, MainActivity2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pi = PendingIntent.getActivity(
            this,
            0,
            i,
            PendingIntent.FLAG_IMMUTABLE
        );

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.bird);

        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
            .Builder(this, CANAL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My title")
            .setContentText("Message...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pi)
            .setLargeIcon(bitmap)
            .setStyle(
                new NotificationCompat.BigTextStyle()
                        .bigText("Descrição...\nDescrição...\nDescrição...\nDescrição...")
            );
    } */

    // Versão 1
    /* private void gerar() {
        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
            .Builder(this, CANAL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("My title")
            .setContentText("Message...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    } */

    private void notificar() {
        int temPermissao = ContextCompat.checkSelfPermission(MainActivity.this, PERMISSAO);
        if (temPermissao == PackageManager.PERMISSION_GRANTED)
            manager.notify(NOTIFICACAO_ID, builder.build());
    }

    private void criarCanalNotificacao() {
        CharSequence nome = "canal 1";
        String descricao = "descricao do canal 1";
        int importancia = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);
        canal.setDescription(descricao);

        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(canal);
    }
}