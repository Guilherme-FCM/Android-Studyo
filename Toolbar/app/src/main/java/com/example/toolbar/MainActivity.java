package com.example.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        listView = findViewById(R.id.listView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        layout.setVisibility(View.INVISIBLE);
        String[] materias = new String[]{};
        switch (item.getItemId()) {
            case R.id.p1:
                materias = getResources().getStringArray(R.array.p1);
                break;
            case R.id.p2:
                materias = getResources().getStringArray(R.array.p2);
                break;
            case R.id.p3:
                materias = getResources().getStringArray(R.array.p3);
                break;
            case R.id.p4:
                materias = getResources().getStringArray(R.array.p4);
                break;
            case R.id.p5:
                materias = getResources().getStringArray(R.array.p5);
                break;
            case R.id.p6:
                materias = getResources().getStringArray(R.array.p6);
                break;
            case android.R.id.home:
                finish();
                break;
            default: return super.onOptionsItemSelected(item);
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, materias));
        return true;
    }
}