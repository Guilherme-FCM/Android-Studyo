package com.example.airplanepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.airplanepermission.databinding.ActivityMainBinding;
import com.example.airplanepermission.databinding.ActivityPeopleAverageBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PeopleAverageActivity extends AppCompatActivity {
    private ActivityPeopleAverageBinding layout;
    private float average;
    private static final int PERMISSIONS_CODE = 1;
    private static final String PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String FILE_NAME = "average.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityPeopleAverageBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());

        average = getPeopleAverage();
        writePeopleAverage();
        requestPermission();
    }

    private float getPeopleAverage() {
        return getIntent().getFloatExtra("average", 0);
    }

    private void writePeopleAverage() {
        layout.text.setText("A média de idade das pessoas cadastradas é de " + average);
    }

    private void requestPermission() {
        int havePermission = ContextCompat.checkSelfPermission(this, PERMISSION);

        if (havePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, PERMISSIONS_CODE);
        }
        else Toast.makeText(this, "Permissão Concedida!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_CODE)
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permissão Concedida! :)", Toast.LENGTH_SHORT).show();
            saveDataOnFile();
        }

        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION))
                requestPermission();

            else Toast.makeText(this, "Permissão NÃO Concedida :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDataOnFile() {
        File parentFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(parentFolder, FILE_NAME);
        byte[] data = ("Média de idade das pessoas: " + average).getBytes();

        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
            Toast.makeText(this, "Dados salvos!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível salvar a média", Toast.LENGTH_SHORT).show();
        }
    }
}