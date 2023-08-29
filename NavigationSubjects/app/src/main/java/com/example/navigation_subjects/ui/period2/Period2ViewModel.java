package com.example.navigation_subjects.ui.period2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period2ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period2ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Fundamentos e Projeto de Banco de Dados", "😒"));
        s.add(new Subject("Análise de Sistemas", "😒"));
        s.add(new Subject("Introdução à Programação", "🤩"));
        s.add(new Subject("Programação Orientada a Objetos", "😭"));
        s.add(new Subject("Programação Básica para Web", "😄"));
        return s;
    }
}
