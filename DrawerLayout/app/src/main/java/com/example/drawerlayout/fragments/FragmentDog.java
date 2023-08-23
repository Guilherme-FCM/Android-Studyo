package com.example.drawerlayout.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drawerlayout.R;
import com.example.drawerlayout.databinding.FragmentDogBinding;
import com.example.drawerlayout.viewmodel.DogViewModel;

public class FragmentDog extends Fragment {
    private FragmentDogBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        binding = FragmentDogBinding.inflate(inflater, container, false);

        DogViewModel dogViewModel = new ViewModelProvider(this).get(DogViewModel.class);

        dogViewModel.getText().observe(getViewLifecycleOwner(), binding.textDog::setText);
        dogViewModel.getImage().observe(getViewLifecycleOwner(), binding.imageDog::setImageResource);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}