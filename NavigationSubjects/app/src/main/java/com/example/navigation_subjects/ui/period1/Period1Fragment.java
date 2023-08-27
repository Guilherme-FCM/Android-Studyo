package com.example.navigation_subjects.ui.period1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.example.navigation_subjects.databinding.FragmentPeriod1Binding;

public class Period1Fragment extends Fragment {
    private FragmentPeriod1Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeriod1Binding.inflate(inflater, container, false);

        Period1ViewModel viewModel = new ViewModelProvider(this).get(Period1ViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), binding.text::setText);

        return binding.getRoot();
    }
}