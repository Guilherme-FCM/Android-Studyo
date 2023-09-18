package com.example.airplanepermission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.airplanepermission.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding layout;
    private List<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());

        setRegisterCountOnView();
        layout.button.setOnClickListener(this::addPerson);

        BroadcastReceiver broadcastReceiver = new AirplaneModeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void addPerson(View v) {
        String name = layout.name.getText().toString();
        int age = Integer.parseInt( layout.age.getText().toString() );

        people.add( new Person(name, age) );
        savePeopleOnSharedPreferences();

        layout.name.setText("");
        layout.age.setText("");

        if (people.size() < 3) setRegisterCountOnView();
        else Toast.makeText(this, "Chega, 3 ta bom!! Ative o modo avião", Toast.LENGTH_SHORT).show();
    }

    private void savePeopleOnSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("people", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("people_average", getPeopleAverage());
        editor.apply();
    }

    private float getPeopleAverage() {
        return (float) people.stream().mapToInt(p -> p.age).average().orElse(0);
    }

    private void setRegisterCountOnView() {
        layout.count.setText(String.valueOf(people.size() + 1));
    }
}