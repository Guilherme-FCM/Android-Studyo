package com.example.moviesfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviesfragments.databinding.AvaliationMovieBinding;

public class AvaliationMovie extends Fragment {
    AvaliationMovieBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AvaliationMovieBinding.inflate(inflater);
        return binding.getRoot();
    }
}
