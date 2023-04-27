package com.example.fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmentos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragManager = getSupportFragmentManager();

        binding.ButtonTela1.setOnClickListener(view -> {
           fragTransaction = fragManager.beginTransaction();
           fragTransaction.replace(R.id.frame, new Tela1());
           fragTransaction.commit();
        });

        binding.ButtonTela2.setOnClickListener(view -> {
            fragTransaction = fragManager.beginTransaction();
            fragTransaction.add(R.id.frame, new Tela2());
            fragTransaction.commit();
        });
    }
}