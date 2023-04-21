package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView product1Image;
    private TextView product1NameView;
    private TextView product1ValueView;

    private ImageView product2Image;
    private TextView product2NameView;
    private TextView product2ValueView;

    private ImageView product3Image;
    private TextView product3NameView;
    private TextView product3ValueView;

    private ImageView product4Image;
    private TextView product4NameView;
    private TextView product4ValueView;

    private TextView payValue;

    private Button payButton;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product1Image = findViewById(R.id.product1Image);
        product1NameView = findViewById(R.id.product1Name);
        product1ValueView = findViewById(R.id.product1Value);

        product2Image = findViewById(R.id.product2Image);
        product2NameView = findViewById(R.id.product2Name);
        product2ValueView = findViewById(R.id.product2Value);

        product3Image = findViewById(R.id.product3Image);
        product3NameView = findViewById(R.id.product3Name);
        product3ValueView = findViewById(R.id.product3Value);

        product4Image = findViewById(R.id.product4Image);
        product4NameView = findViewById(R.id.product4Name);
        product4ValueView = findViewById(R.id.product4Value);

        payButton = findViewById(R.id.payButton);

        String p1Name = getTextView(product1NameView);
        double p1Value = Double.parseDouble(getTextView(product1ValueView));

        String p2Name = getTextView(product2NameView);
        double p2Value = Double.parseDouble(getTextView(product2ValueView));

        String p3Name = getTextView(product3NameView);
        double p3Value = Double.parseDouble(getTextView(product3ValueView));

        String p4Name = getTextView(product4NameView);
        double p4Value = Double.parseDouble(getTextView(product4ValueView));

        product1Image.setOnClickListener(generateProductActivity(p1Name, p1Value));
        product2Image.setOnClickListener(generateProductActivity(p2Name, p2Value));
        product3Image.setOnClickListener(generateProductActivity(p3Name, p3Value));
        product4Image.setOnClickListener(generateProductActivity(p4Name, p4Value));

        Intent intent = getIntent();
        payValue = findViewById(R.id.payValue);
        total = intent.getDoubleExtra("total", 0);
        payValue.setText("Total: R$ " + total);

        payButton.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, PaymentActivity.class);
            it.putExtra("total", total);
            startActivity(it);
        });
    }

    private View.OnClickListener generateProductActivity(String name, double value) {
        return view -> {
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("value", value);
            intent.putExtra("total", total);
            startActivity(intent);
        };
    }

    private String getTextView(TextView textView) {
        return textView.getText().toString();
    }
}