package com.example.drawerlayout.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drawerlayout.R;

public class DogViewModel extends ViewModel {
    private final MutableLiveData<String> text = new MutableLiveData<>();
    private final MutableLiveData<Integer> image = new MutableLiveData<>();

    public DogViewModel() {
        text.setValue("DOG");
        image.setValue(R.drawable.dog);
    }

    public MutableLiveData<String> getText() {
        return text;
    }

    public MutableLiveData<Integer> getImage() {
        return image;
    }
}
