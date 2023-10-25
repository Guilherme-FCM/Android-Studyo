package com.example.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TimePicker timePicker;
    private Button buttonClicar;
    private TextView textView, textViewTime;
    private TimePickerDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        buttonClicar = findViewById(R.id.buttonClicar);
        textView = findViewById(R.id.textViewTime);
        textViewTime = findViewById(R.id.textViewTime2);

        timePicker.setIs24HourView(true);
        buttonClicar.setOnClickListener((view) -> {
            obter();
        });
    }

    public void clicar(View view) {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR);
        int minuto = calendar.get(Calendar.MINUTE);
        dialog = new TimePickerDialog(this, this, hora, minuto, true);
        dialog.show();
    }

    private void obter(){
        int hora = 0, minuto = 0;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            hora=timePicker.getHour();
            minuto=timePicker.getMinute();
        }else{
            hora=timePicker.getCurrentHour();
            minuto=timePicker.getCurrentMinute();
        }
        String tempo = hora+":"+minuto;
        textView.setText(tempo);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        String time = h+":"+m;
        textViewTime.setText(time);
    }
}
