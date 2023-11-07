package com.example.contentprovidermessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void whats(View view) {
        String text = "Mensagem";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }

    public void email(View view){
        String subject = "MyTitulo";
        String text = "MyTexto";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"erickpart28@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.setType("text/plain");
        intent.setPackage("com.google.android.gm");
        startActivity(intent);
    }

    public void sms(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this);
            intent.setPackage(defaultSmsPackage);
            intent.setType("text/plain");
        }
        else {
            intent.setType("vnd.android-dir/mms-sms");
        }
        startActivity(intent);
   }
}