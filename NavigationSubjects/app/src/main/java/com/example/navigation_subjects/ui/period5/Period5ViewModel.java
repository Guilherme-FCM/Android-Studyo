package com.example.navigation_subjects.ui.period5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period5ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period5ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Segurança de Sistemas", "😭"));
        s.add(new Subject("Infraestrutura para Sistemas Web", "😄"));
        s.add(new Subject("Administração de Banco de Dados", "🤩"));
        s.add(new Subject("Novas Tecnologias em BD", "🤩"));
        s.add(new Subject("Programação para Dispositivos Móveis I", "🤩"));
        s.add(new Subject("Metodologias de Desenvolvimento", "😐"));
        return s;
    }
}
