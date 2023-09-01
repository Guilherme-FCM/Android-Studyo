package com.example.navigation_subjects.ui.period6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period6ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period6ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Novas Tecnologias em Desenvolvimento para Web", "😄"));
        s.add(new Subject("Gerência de Configuração", "😄"));
        s.add(new Subject("Padrões de Projeto", "🤩"));
        s.add(new Subject("Programação para Dispositivos Móveis II", "😭"));
        return s;
    }
}
