package com.example.moviesfragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moviesfragments.databinding.ImageMovieBinding;

public class ImageMovie extends Fragment {
    ImageMovieBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ImageMovieBinding.inflate(inflater);
        return binding.getRoot();
    }
}
