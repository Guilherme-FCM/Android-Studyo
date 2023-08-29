package com.example.navigation_subjects.ui.period3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period3ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period3ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Introdução à Conectividade", "😐"));
        s.add(new Subject("Gerenciamento de Dados para Web", "😄"));
        s.add(new Subject("Programação para Banco de Dados", "🤩"));
        s.add(new Subject("Administração de Sistemas Proprietários", "😭"));
        s.add(new Subject("Programação para Web Designers", "😐"));
        s.add(new Subject("Programação para Web I", "🤩"));
        return s;
    }
}
