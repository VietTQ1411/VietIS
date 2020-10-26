package com.example.vietis.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vietis.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        },2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitAnimation();
            }
        }, 5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },6000);
    }

    private void exitAnimation() {
        imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.imagine_out));
        textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_out));
        textView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_out));
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
    }

    private void startAnimation() {
        imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.imagine_in));
        textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_in));
        textView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_in));
        imageView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
    }
}