package com.example.caracoroua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class GameActivity extends AppCompatActivity {
    private int selectedCoin = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void onCoinClick(View view) {
        RadioButton radio = (RadioButton) view;
        if (radio.isChecked()) {
            switch (radio.getId()) {
                case R.id.imageCara:
                    selectedCoin = 0;
                break;
                case R.id.imageCoroa:
                    selectedCoin = 1;
                break;
            }
        }
    }

    public void onPlayClick(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("coin", selectedCoin);
        startActivity(intent);
    }
}