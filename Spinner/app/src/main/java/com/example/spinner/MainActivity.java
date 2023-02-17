package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private ProgressBar progressBar;
    int progress = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.determinateBar);
        adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_expandable_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener((view) -> {
            handler = new Handler();
            progress = progressBar.getProgress();
            new Thread(() -> {
               while (progress < 100) {
                   progress += 2;
                   handler.post(() -> {
                       progressBar.setProgress(progress);
                   });
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException error) {
                       error.printStackTrace();
                   }
               }
            }).start();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String planet = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, planet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}