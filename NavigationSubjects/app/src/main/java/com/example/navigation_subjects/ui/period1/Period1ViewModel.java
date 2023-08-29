package com.example.navigation_subjects.ui.period1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period1ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period1ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Fundamentos de Redes de Computadores", "😒"));
        s.add(new Subject("Tendências Tecnológicas para o Mercado de TI", "😄"));
        s.add(new Subject("Introdução a Computação", "🤩"));
        s.add(new Subject("Informática Instrumental", "😭"));
        s.add(new Subject("Introdução à Lógica", "🤩"));
        s.add(new Subject("Inglês Técnico", "😐"));
        return s;
    }
}
