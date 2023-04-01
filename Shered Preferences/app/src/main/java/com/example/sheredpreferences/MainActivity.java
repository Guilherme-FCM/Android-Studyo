package com.example.sheredpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPass;
    private Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.name);
        editTextPass = findViewById(R.id.pass);
        buttonEnviar = findViewById(R.id.button);

        buttonEnviar.setOnClickListener((view) -> {
            String name = editTextName.getText().toString();
            String pass = editTextPass.getText().toString();
            SharedPreferences sp = getSharedPreferences("dados", MODE_PRIVATE);

            if (
                sp.contains("name") &&
                sp.contains("pass") &&
                name.equals(sp.getString("name", ""))
            ) {
                if (pass.equals(sp.getString("pass", "")))
                    startActivity(new Intent(this, FinalActivity.class));
                else Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", name);
                editor.putString("pass", pass);
                editor.apply();
                startActivity(new Intent(this, QuestionActivity.class));
            }
        });
    }
}