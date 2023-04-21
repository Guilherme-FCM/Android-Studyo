package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
    private TextView textView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textView = findViewById(R.id.textView);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        double total = intent.getDoubleExtra("total", 0);
        textView.setText("Valor pago: R$ " + total);

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(PaymentActivity.this, MainActivity.class));
        });
    }
}