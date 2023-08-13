package com.example.gson_executor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://jsonplaceholder.typicode.com/posts";
    private StringBuilder builder = null;
    private List<User> dadosBaixados = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            new ObterDados().execute();
            handler.post(() -> {
                TextView textViewDados = findViewById(R.id.textViewDados);
                textViewDados.setText(builder.toString());
            });
        });
    }

    private class ObterDados {

        public void execute() {
            Conexao conexao = new Conexao();
            InputStream inputStream = conexao.obterRespostaHttp(URL);

            Auxilia auxilia = new Auxilia();
            String textJson = auxilia.converter(inputStream);

            Log.i("JSON", "doInBackground: " + textJson);

            Gson gson = new Gson();
            builder = new StringBuilder();

            if (textJson != null) {
                Type type = new TypeToken<List<User>>(){}.getType();
                dadosBaixados = gson.fromJson(textJson, type);

                for (User user : dadosBaixados) {
                    builder.append(user.getTitle()).append("\n\n\n");
                }
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Erro no JSON", Toast.LENGTH_SHORT).show();
                });
            }
        }
    }
}