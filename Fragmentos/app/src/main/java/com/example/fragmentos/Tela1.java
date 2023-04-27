package com.example.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentos.databinding.Tela1Binding;

public class Tela1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        Tela1Binding binding = Tela1Binding.inflate(inflater);
        binding.button.setOnClickListener(view -> {
            binding.textView.setText(binding.editText.getText().toString());
        });
        return binding.getRoot();
    }
}
