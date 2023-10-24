package com.example.timepickeralarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CANAL_ID = "1";
    private static final int NOTIFICACAO_ID = 2;
    private static final String PERMISSAO = Manifest.permission.POST_NOTIFICATIONS;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::sendNotification);
    }

    private void sendNotification(View v) {
        int temPermissao = ContextCompat.checkSelfPermission(MainActivity.this, PERMISSAO);
        if (temPermissao == PackageManager.PERMISSION_GRANTED)
            manager.notify(NOTIFICACAO_ID, builder.build());
    }

    private void config() {
        CharSequence nome = "canal 1";
        String descricao = "descricao do canal 1";
        int importancia = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel canal = new NotificationChannel(CANAL_ID, nome, importancia);
        canal.setDescription(descricao);

        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(canal);

        Intent i = new Intent(this, Activity2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(
                this,
                0,
                i,
                PendingIntent.FLAG_IMMUTABLE
        );

        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
                .Builder(this, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notificação JSON")
                .setContentText("Clique aqui para consumir o json")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi);
    }
}