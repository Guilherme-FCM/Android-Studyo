package com.example.audiorecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    Button recordMediaRecorder;
    Button playMediaPlayer;
    boolean isRecordingMedia = false;
    boolean isPlayingMedia = false;
    String fileNameMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playMediaPlayer = findViewById(R.id.play_mediaplayer);
        recordMediaRecorder = findViewById(R.id.record_mediarecorder);

        fileNameMedia = getFilesDir().getPath() + "/testfile" + ".3gp";
        File fileMedia = new File(fileNameMedia);

        if (!fileMedia.exists()) {
            try {
                fileMedia.createNewFile();
            } catch (IOException e) {
                Log.d(TAG, "could not create file " + e.toString());
                e.printStackTrace();
            }
        }

        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    200
            );
        }

        setListeners();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode != 200){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission to record audio granted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Permission to record audio denied", Toast.LENGTH_LONG).show();
            playMediaPlayer.setEnabled(false);
            recordMediaRecorder.setEnabled(false);
        }
    }

    private void setListeners() {
        recordMediaRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRecordingMedia) {
                    startRecording(recordMediaRecorder.getId());
                }
                else {
                    stopRecording(recordMediaRecorder.getId());
                }
                isRecordingMedia = !isRecordingMedia;
                setButtonText();
            }
        });

        playMediaPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlayingMedia) {
                    startPlaying(playMediaPlayer.getId());
                }
                else {
                    stopPlaying(playMediaPlayer.getId());
                }
                isPlayingMedia = !isPlayingMedia;
                setButtonText();
            }
        });
    }
    private void startRecording(int id) {
        if (id == R.id.record_mediarecorder) {
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFile(fileNameMedia);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

                try {
                    mediaRecorder.prepare();
                } catch (IOException e) {
                    // handle error
                    Toast.makeText(this, "IOException while trying to prepare MediaRecorder", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "could not prepare MediaRecorder " + e.toString());
                    return;
                } catch (IllegalStateException e) {
                    // handle error
                    Toast.makeText(this, "IllegalStateException while trying to prepare MediaRecorder", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "could not prepare MediaRecorder " + e.toString());
                    return;
                }

                mediaRecorder.start();
                Log.d(TAG, "recording started with MediaRecorder");
            }
        }
    }

    private void stopRecording(int id) {
        if (id == R.id.record_mediarecorder) {
            if (mediaRecorder != null) {

                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();

                mediaRecorder = null;
            }
        }
    }
    private void startPlaying(int id) {
        if (id == R.id.play_mediaplayer) {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                        mediaPlayer = null;
                        isPlayingMedia = false;
                        setButtonText();
                    }
                });


                try {
                    mediaPlayer.setDataSource(fileNameMedia);

                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Log.d(TAG, "playback started with MediaPlayer");
                } catch (IOException e) {
                    Toast.makeText(this, "Couldn't prepare MediaPlayer, IOException", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "error reading from file while preparing MediaPlayer" + e.toString());
                } catch (IllegalArgumentException e) {
                    Toast.makeText(this, "Couldn't prepare MediaPlayer, IllegalArgumentException", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "illegal argument given " + e.toString());
                }
            }
        }
    }

    private void stopPlaying(int id) {
        if (id == R.id.play_mediaplayer) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }

    private void setButtonText() {
        if (isRecordingMedia) {
            recordMediaRecorder.setText(R.string.record_button_stop);
        }
        else {
            recordMediaRecorder.setText(R.string.record_mediarecorder);
        }

        if (isPlayingMedia) {
            playMediaPlayer.setText(R.string.play_button_stop);
        }
        else {
            playMediaPlayer.setText(R.string.play_mediaplayer);
        }
    }


}