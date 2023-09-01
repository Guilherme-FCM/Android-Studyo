package com.example.navigation_subjects.ui.period5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigation_subjects.databinding.FragmentPeriodBinding;
import com.example.navigation_subjects.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class Period5Fragment extends Fragment {
    private FragmentPeriodBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeriodBinding.inflate(inflater, container, false);

        Period5ViewModel viewModel = new ViewModelProvider(this).get(Period5ViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), this::setSubjects);

        return binding.getRoot();
    }

    private void setSubjects(List<Subject> subjects) {
        List<String> list = subjects.stream().map(Subject::toString).collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        binding.list.setAdapter(adapter);
    }
}