package com.example.json_studenta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.json_studenta.databinding.ActivityMainBinding;
import com.example.json_studenta.entities.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String URL = "https://raw.githubusercontent.com/Guilherme-FCM/Android-Studyo/master/db.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<Student> students = getStudents();
            handler.post(() -> {
                showData(students);
            });
        });
    }

    private List<Student> getStudents() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Student>>(){}.getType();
        try {
            Request request = new Request(URL);
            return gson.fromJson(request.getResponse(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showData(List<Student> data) {
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, data);
        binding.listView.setAdapter(adapter);
    }
}