package com.example.moviesfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviesfragments.databinding.SinopseMovieBinding;

public class SinopseMovie extends Fragment {
    SinopseMovieBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SinopseMovieBinding.inflate(inflater);
        return binding.getRoot();
    }
}
