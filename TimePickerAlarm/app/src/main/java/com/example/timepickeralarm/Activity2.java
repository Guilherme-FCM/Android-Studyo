package com.example.timepickeralarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Activity2 extends AppCompatActivity {
    public static final String URL = "https://raw.githubusercontent.com/Guilherme-FCM/Lucky-App/master/app/src/main/res/data.json";

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        text = findViewById(R.id.text);

        new Task().execute();
    }

    private class Task extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                HttpRequest request = new HttpRequest(URL);
                return request.get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            text.setText(result);
        }
    }
}