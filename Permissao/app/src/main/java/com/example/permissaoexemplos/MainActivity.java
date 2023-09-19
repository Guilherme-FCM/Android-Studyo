package com.example.permissaoexemplos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private Button btnPermissao;
    private static final int CODIGO_SOLICITACAO = 1;
    private static final String PERMISSOES = Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPermissao = findViewById(R.id.buttonHello);
        btnPermissao.setOnClickListener(v -> {
            solicitarPermissao();
        });
    }

    private void solicitarPermissao() {
       int temPermissao = ContextCompat.checkSelfPermission(this, PERMISSOES);
         if (temPermissao != PackageManager.PERMISSION_GRANTED) {
              ActivityCompat.requestPermissions(this, new String[]{PERMISSOES}, CODIGO_SOLICITACAO);
         } else {
             chamarActivity2();
         }
    }

    private void chamarActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode!= CODIGO_SOLICITACAO){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        return;
        }
        if(grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chamarActivity2();
            } else {
               if(ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSOES)){
                   AlertDialog.Builder builder = new AlertDialog.Builder(this);
                     builder.setTitle("Atenção")
                             .setMessage("É necessário a permissão para acessar a câmera")
                             .setCancelable(false)
                             .setPositiveButton("Sim", (dialog, which) -> {
                                 ActivityCompat.requestPermissions(this, new String[]{PERMISSOES}, CODIGO_SOLICITACAO);
                             })
                   .setNegativeButton("Não", (dialog, which) -> {
                       Toast.makeText(this, "e necessario para funcionar...ADEUS!", Toast.LENGTH_SHORT).show();
                          finish();
                   });
                     AlertDialog dialog = builder.create();
                        dialog.show();
               }
               else {
                   finish();
               }
            }
        }
    }
}
