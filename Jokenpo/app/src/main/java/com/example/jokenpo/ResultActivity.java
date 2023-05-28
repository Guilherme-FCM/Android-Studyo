package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.example.jokenpo.databinding.ActivityMainBinding;
import com.example.jokenpo.databinding.ActivityResultBinding;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sp = getSharedPreferences("jokenpo", MODE_PRIVATE);
        String username = sp.getString("username", "");

        int userSelect = getIntent().getIntExtra("selected", -1);
        int gameSelect = randomSelect();

        String[] cardNames = getResources().getStringArray(R.array.jokenpoNames);
        TypedArray cardDrawables = getResources().obtainTypedArray(R.array.jokenpoDrawables);

        binding.cardUser.img.setImageResource(cardDrawables.getResourceId(userSelect, 0));
        binding.cardUser.name.setText(username);

        binding.cardGame.img.setImageResource(cardDrawables.getResourceId(gameSelect, 0));
        binding.cardGame.name.setText("Jogo");

        binding.textView.setText(
                "O jogador " + username + " escolheu " + cardNames[userSelect] + "\n" +
                "O jogo escolheu " + cardNames[gameSelect] + "\n" +
                getResult(userSelect, gameSelect)
        );
    }

    public int randomSelect() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public String getResult(int user, int game) {
        if (user == game) return "Empate!";
        else if ((user == 0 && game == 2) || (user == 1 && game == 0) || (user == 2 && game == 1))
            return "VOCÊ VENCEU!";
        else return "VOCÊ PERDEU :(";

    }
}