package com.example.permissaoexemplos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private static final int FOTO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        acionarCamera();
    }

    private void acionarCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, FOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            if(requestCode != FOTO ){
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            if(resultCode == MainActivity2.RESULT_OK){
               Toast.makeText(this, "Coloque aqui" + "Caminho para salvar a foto", Toast.LENGTH_SHORT).show();
            }
    }
}