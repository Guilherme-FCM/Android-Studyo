package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;

import com.example.room.fragments.CreateCachorroFragment;
import com.example.room.fragments.ListCachorroFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Database db;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // É necessário utilizar uma Thread secundária -> allowMainThreadQueries = gambiarra para burlar
        db = Room.databaseBuilder(this, Database.class, "banco_room").allowMainThreadQueries().build();

        fragManager = getSupportFragmentManager();

        renderFragment(new ListCachorroFragment(db.cachorroDao()));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            renderFragment(new CreateCachorroFragment(db.cachorroDao()));
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    void renderFragment(Fragment fragment) {
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame, fragment);
        fragTransaction.commit();
    }
}