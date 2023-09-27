package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CANAL_ID = "1";
    private static final int NOTIFICACAO_ID = 2;
    private static final int SOLICITACAO_ID = 3;
    private static final String PERMISSAO = Manifest.permission.POST_NOTIFICATIONS;
    private Button button;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;

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

    private void gerar() {
        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
            .Builder(this, CANAL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("My title")
            .setContentText("Message...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

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