package com.example.navigation_subjects.ui.period4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigation_subjects.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Period4ViewModel extends ViewModel {
    private MutableLiveData<List<Subject>> subjects;

    public Period4ViewModel() {
        subjects = new MutableLiveData<>(getSubjects());
    }

    public LiveData<List<Subject>> getText() {
        return subjects;
    }

    private List<Subject> getSubjects() {
        List<Subject> s = new ArrayList<>();
        s.add(new Subject("Sistemas Distribuídos e SOA", "🤩"));
        s.add(new Subject("Projeto de Sistemas", "😐"));
        s.add(new Subject("Testes de Software", "🤩"));
        s.add(new Subject("Projeto de Interface Web", "😒"));
        s.add(new Subject("Programação para Web II", "🤩"));
        s.add(new Subject("Gestão de Projetos (eletiva)", "😄"));
        return s;
    }
}
