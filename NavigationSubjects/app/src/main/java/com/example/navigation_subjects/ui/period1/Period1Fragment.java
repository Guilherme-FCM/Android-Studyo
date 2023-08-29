package com.example.navigation_subjects.ui.period1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.navigation_subjects.databinding.FragmentPeriodBinding;
import com.example.navigation_subjects.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class Period1Fragment extends Fragment {
    private FragmentPeriodBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeriodBinding.inflate(inflater, container, false);

        Period1ViewModel viewModel = new ViewModelProvider(this).get(Period1ViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), this::setSubjects);

        return binding.getRoot();
    }

    private void setSubjects(List<Subject> subjects) {
        List<String> list = subjects.stream().map(Subject::toString).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        binding.list.setAdapter(adapter);
    }
}