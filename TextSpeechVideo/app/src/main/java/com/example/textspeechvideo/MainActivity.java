package com.example.textspeechvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
    }

    public void clicar(View view) {
        if (view.getId() == R.id.button) {
            String nome = editText.getText().toString();
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("nome", nome);
            startActivity(intent);
        }
    }
}