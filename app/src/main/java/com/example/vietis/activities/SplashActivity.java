package com.example.vietis.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietis.R;
import com.example.vietis.inteface.IView;
import com.example.vietis.notifications.MyFirebaseMessagingService;
import com.example.vietis.view_model.SplashActivityViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class SplashActivity extends AppCompatActivity implements IView {

    private static final String TAG = "Notification";
    private ImageView imageView;
    private TextView textView;
    private TextView textView2;
    private SplashActivityViewModel splashActivityViewModel = new SplashActivityViewModel();

    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    MyFirebaseMessagingService.CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1 ");
            channel1.enableLights(true);
            channel1.setLightColor(Color.CYAN);
            channel1.enableVibration(true);
            channel1.setVibrationPattern(new long[]{100, 400, 200, 400});
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Log.w(TAG,"Fetching FCM registration token failed",task.getException());
                    return;
                }
                String token = task.getResult();
                splashActivityViewModel.deviceRegister(token,"");
                splashActivityViewModel.getMsg().observe(SplashActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String msg) {
                        if(!msg.equals("")){
                            Toast.makeText(SplashActivity.this,msg,Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        createNotificationChannels();
        setContentView(R.layout.activity_splash);
        mappingUI();
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
        imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.image_out));
        textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_out));
        textView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_out));
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
    }

    private void startAnimation() {
        imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.image_in));
        textView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_in));
        textView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.text_in));
        imageView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
    }

    @Override
    public void mappingUI() {
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    @Override
    public void setupUI() {

    }
}