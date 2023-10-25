package com.example.mediaplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mediaplayer.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private List<Integer> randomNumbers = new ArrayList<>();
    private final int GENERATED_RANDOM_NUMBERS_AMOUNT = 5;
    private final int INTERVAL = 2 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.result.setOnClickListener(this::showResult);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            Toast.makeText(MainActivity.this, "Fim da música 👏👏", Toast.LENGTH_SHORT).show();
        });
    }

    private void generateRandomNumbers() {
        Random random = new Random();
        Handler handler = new Messanger();
        randomNumbers.clear();

        new Thread(() -> {
            for (int i = 0; i <= GENERATED_RANDOM_NUMBERS_AMOUNT; i++) {
                Message msg = new Message();
                int generated = random.nextInt(10);

                msg.obj = i != GENERATED_RANDOM_NUMBERS_AMOUNT
                        ? generated
                        : "Aperte o PLAY";

                randomNumbers.add(generated);
                handler.sendMessage(msg);

                try {
                    Thread.sleep(INTERVAL);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private class Messanger extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            binding.random.setText(String.valueOf(msg.obj));
        }
    }

    private boolean validateResponse(int response) {
        return response == randomNumbers.stream().mapToInt(i -> i).sum();
    }

    public void playMusic(View view) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            generateRandomNumbers();
        }
    }

    public void pauseMusic(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
        }
    }

    public void showResult(View view) {
        try {
            String input = binding.input.getText().toString();
            String msg = validateResponse(Integer.parseInt(input)) ? "Acertou" : "Errou";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            Log.i("RESULTADO", msg);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Deve informar somente números inteiros", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}