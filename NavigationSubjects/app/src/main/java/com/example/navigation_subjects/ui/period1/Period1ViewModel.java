package com.example.navigation_subjects.ui.period1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Period1ViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public Period1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Periodo 1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
