package com.example.contentproviderapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.textName);
        textViewResult = findViewById(R.id.res);
    }

    public void onClickAddDetails(View view){
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.name,
                editTextName.getText().toString());
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
        Toast.makeText(this, "new record inserted", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("Range")
    public void onClickShowDetails(View view){
        Cursor cursor = getContentResolver().query(
                MyContentProvider.CONTENT_URI,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            StringBuilder stringBuilder = new StringBuilder();
            while(!cursor.isAfterLast()) {
                stringBuilder.append("\n"+cursor.getString(cursor.getColumnIndex(MyContentProvider.id)));
                stringBuilder.append("-"+cursor.getString(cursor.getColumnIndex(MyContentProvider.name)));
                cursor.moveToNext();
            }
            textViewResult.setText(stringBuilder);
        }
        else{
            textViewResult.setText("no records found");
        }
    }
}