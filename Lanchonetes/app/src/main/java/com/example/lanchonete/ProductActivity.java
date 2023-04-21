package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity {
    private TextView textViewName;
    private TextView textViewValue;
    private EditText editText;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        textViewName = findViewById(R.id.textViewName);
        textViewValue = findViewById(R.id.textViewValue);
        editText = findViewById(R.id.editText);
        finishButton = findViewById(R.id.finishButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        double value = intent.getDoubleExtra("value", 0);
        double total = intent.getDoubleExtra("total", 0);
        textViewName.setText(name);
        textViewValue.setText("Valor unitário: R$ " + value);

        finishButton.setOnClickListener(view -> {
            Intent it = new Intent(ProductActivity.this, MainActivity.class);
            int amount = 0;
            try {
                amount = Integer.parseInt(editText.getText().toString());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            it.putExtra("total", total + (value * amount));
            startActivity(it);
        });
    }
}