package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityJavascript extends AppCompatActivity {
    private RadioButton radioButtonA, radioButtonB, radioButtonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javascript);

        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);

    }

    public void onRadioButtonClicked(View v) {
        RadioButton view = (RadioButton) v;
        boolean selected = view.isChecked();
        switch (view.getId()) {
            case R.id.radioButtonA:
                if(selected)
                    Toast.makeText(this, "Acertou", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButtonB:
                if(selected)
                    radioButtonB.setVisibility(RadioButton.INVISIBLE);
                break;

            case R.id.radioButtonC:
                radioButtonC.setVisibility(RadioButton.INVISIBLE);
                break;
        }
    }
}