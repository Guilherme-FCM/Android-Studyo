package com.example.lottie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationView = findViewById(R.id.animationView);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {
                Toast.makeText(MainActivity.this, "Página não encontrada :(", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                Toast.makeText(MainActivity.this, "Já deu rs", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {
                Toast.makeText(MainActivity.this, "Repetindo Animação", Toast.LENGTH_SHORT).show();
            }
        });
    }
}