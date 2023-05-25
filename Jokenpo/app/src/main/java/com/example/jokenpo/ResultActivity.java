package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

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

        String[] cardNames = getResources().getStringArray(R.array.jokenpoNames);

        int userSelect = getIntent().getIntExtra("selected", -1);
        int gameSelect = randomSelect();


        // Add nome do usuário | melhorar msg final
        binding.textView.setText("Você escolheu " + cardNames[userSelect] + " e o jogo escolheu " + cardNames[gameSelect] + "\n"+ getResult(userSelect, gameSelect));
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