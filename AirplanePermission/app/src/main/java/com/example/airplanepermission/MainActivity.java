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

        layout.name.setText("");
        layout.age.setText("");

        if (people.size() < 3) setRegisterCountOnView();
        else Toast.makeText(this, "Chega, 3 ta bom!! Ative o modo avião", Toast.LENGTH_SHORT).show();
    }

    private float getPeopleAverage() {
        return (float) people.stream().mapToInt(p -> p.age).average().orElse(0);
    }

    private void setRegisterCountOnView() {
        layout.count.setText(String.valueOf(people.size() + 1));
    }

    public class AirplaneModeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Avião sem asa, fogueira sem brasa, sou eu assim sem você.", Toast.LENGTH_LONG).show();

            Intent it = new Intent(context, PeopleAverageActivity.class);
            it.putExtra("average", getPeopleAverage());
            context.startActivity(it);
        }
    }
}