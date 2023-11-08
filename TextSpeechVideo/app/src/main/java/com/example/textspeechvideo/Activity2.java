package com.example.textspeechvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;


import java.util.Locale;


public class Activity2 extends AppCompatActivity
        implements TextToSpeech.OnInitListener
{
    private VideoView videoView;
    private Button buttonPlay,buttonPause,buttonStop;
    private Uri uri;
    private TextToSpeech textToSpeech;
    private String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);


        String nome = getIntent().getStringExtra("nome");
        msg = nome+" seu vídeo está pronto";
        textToSpeech = new TextToSpeech(getApplicationContext(),
                this );
        videoView = findViewById(R.id.videoView);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonStop = findViewById(R.id.buttonStop);
        uri = Uri.parse("android.resource://"
                +getPackageName()+"/"+R.raw.video);
        videoView.setVideoURI(uri);
    }//onCreate


    @Override
    public void onInit(int i) {
        if(i==TextToSpeech.SUCCESS){
            Locale locale = new Locale("pt","br");
            int result = textToSpeech.setLanguage(locale);
            textToSpeech.setSpeechRate(0.5f);
            if(result==TextToSpeech.LANG_MISSING_DATA
                    || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("Activity2","problemas com o idioma");
            }//
            else{
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                    textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,
                            null,null);
                }else{
                    textToSpeech.speak(msg,TextToSpeech.QUEUE_FLUSH,
                            null);
                }
            }
        }//if
        else{
            Log.e("Activity2","problema com o TextToSpeech");
        }
    }


    public void clicar(View view){
        if(view.getId()==R.id.buttonPlay){
            int duracao = videoView.getDuration();
            Toast.makeText(this, "tempo total: "
                    +duracao, Toast.LENGTH_SHORT).show();
            videoView.start();
        }//
        if(view.getId()==R.id.buttonPause){
            int posicao = videoView.getCurrentPosition();
            Toast.makeText(this, "tempo atual: "
                    +posicao, Toast.LENGTH_SHORT).show();
            videoView.pause();
        }
        if(view.getId()==R.id.buttonStop){
            Toast.makeText(this, "vídeo não pode ser" +
                    "mais reproduzido", Toast.LENGTH_SHORT).show();
            videoView.stopPlayback();
            finish();
        }//
    }//
}//class
