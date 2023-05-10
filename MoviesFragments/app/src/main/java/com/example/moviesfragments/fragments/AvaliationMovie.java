package com.example.moviesfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviesfragments.databinding.AvaliationMovieBinding;

public class AvaliationMovie extends Fragment {
    AvaliationMovieBinding binding;
    private String username;

    public AvaliationMovie() {}
    public AvaliationMovie(String username) {
        this.username = username;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AvaliationMovieBinding.inflate(inflater);

        if (this.username.length() > 0) binding.textView.setText("Olá, " + username);

        binding.button.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Avaliação enviada!", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}
