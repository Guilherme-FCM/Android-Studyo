package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.jokenpo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.play.setOnClickListener((view -> {
            String username = binding.username.getText().toString();
            SharedPreferences sp = getSharedPreferences("jokenpo", MODE_PRIVATE);

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", username);
            editor.apply();

            startActivity(new Intent(this, SelectActivity.class));
        }));
    }
}