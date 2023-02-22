package com.example.pesoplanetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private int posicaoPlanetaEscolhido;
    private String[] planetas;
    private TypedArray pesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        FloatingActionButton button = findViewById(R.id.floatButton);

        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(this);

        planetas = getResources().getStringArray(R.array.planetas);
        pesos = getResources().obtainTypedArray(R.array.pesos);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, this.planetas);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, planetas[i], Toast.LENGTH_SHORT).show();
        this.posicaoPlanetaEscolhido = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        TextView textView = findViewById(R.id.result);
        EditText editText = findViewById(R.id.input);

        double peso_usuario = Double.parseDouble( editText.getText().toString() );
        double peso_planeta = this.pesos.getFloat(posicaoPlanetaEscolhido, 0);
        String peso_final = String.format("%.2f", peso_usuario * peso_planeta);

        String planeta = planetas[posicaoPlanetaEscolhido];
        textView.setText("Seu peso no planeta " + planeta + " será de " + peso_final + " Kg");
    }
}