package com.example.gamenotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gamenotification.databinding.Activity2Binding;
import com.example.gamenotification.databinding.ActivityMainBinding;

public class Activity2 extends AppCompatActivity {
    private Activity2Binding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = Activity2Binding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());

        layout.average.setText(String.valueOf(getAverage()));
    }

    private float getAverage() {
        SharedPreferences sharedPreferences = getSharedPreferences("MinhasPreferencias", MODE_PRIVATE);
        return sharedPreferences.getFloat("average", 0);
    }
}