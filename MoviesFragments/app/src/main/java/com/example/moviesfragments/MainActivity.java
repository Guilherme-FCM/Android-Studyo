package com.example.moviesfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.moviesfragments.databinding.ActivityMainBinding;
import com.example.moviesfragments.fragments.AvaliationMovie;
import com.example.moviesfragments.fragments.ImageMovie;
import com.example.moviesfragments.fragments.SinopseMovie;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ActivityMainBinding binding;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragManager = getSupportFragmentManager();

        binding.radioGroupSelection.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.radioButton) this.renderFragment(new ImageMovie());
        else if (i == R.id.radioButton2) this.renderFragment(new SinopseMovie());
        else if (i == R.id.radioButton3) this.renderFragment(new AvaliationMovie());
    }

    private void renderFragment(Fragment fragment) {
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frameLayout, fragment);
        fragTransaction.commit();
    }
}