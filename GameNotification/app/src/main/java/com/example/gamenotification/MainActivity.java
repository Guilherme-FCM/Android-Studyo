package com.example.gamenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gamenotification.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding layout;
    private List<Game> games = new ArrayList<>();
    private final String CANAL_ID = "1";
    private static final int NOTIFICACAO_ID = 2;
    private static final String PERMISSAO = Manifest.permission.POST_NOTIFICATIONS;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());

        layout.button.setOnClickListener(this::addGame);
        criarCanalNotificacao();
        configNotification();
    }

    private void addGame(View v) {
        try {
            String name = layout.name.getText().toString();
            double price = Double.parseDouble( layout.price.getText().toString() );
            String gender = layout.gender.getText().toString();
            String platform = layout.platform.getText().toString();

            games.add( new Game(name, price, gender, platform) );
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível cadastrar este jogo", Toast.LENGTH_SHORT).show();
        }

        cleanForm();

        if (games.size() < 3) setRegisterCountOnView();
        else {
            saveAverage();
            notifyApp();
        }
    }

    private void cleanForm() {
        layout.name.setText("");
        layout.price.setText("");
        layout.gender.setText("");
        layout.platform.setText("");
    }

    private double getAverage() {
        return games.stream()
                .mapToDouble(Game::getPrice)
                .average()
                .orElse(0);
    }

    private void saveAverage() {
        SharedPreferences sharedPreferences = getSharedPreferences("MinhasPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("average", (float) getAverage());
        editor.apply();
    }

    private void setRegisterCountOnView() {
        layout.count.setText(String.valueOf(games.size() + 1));
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

    private void notifyApp() {
        int temPermissao = ContextCompat.checkSelfPermission(MainActivity.this, PERMISSAO);
        if (temPermissao == PackageManager.PERMISSION_GRANTED)
            manager.notify(NOTIFICACAO_ID, builder.build());
    }

    private void configNotification() {
        Intent i = new Intent(this, Activity2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pi = PendingIntent.getActivity(this, 0,i,PendingIntent.FLAG_IMMUTABLE);

        manager = NotificationManagerCompat.from(MainActivity.this);
        builder = new NotificationCompat
                .Builder(this, CANAL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notificação!")
                .setContentText("já foi atingido o limite")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi);
    }
}