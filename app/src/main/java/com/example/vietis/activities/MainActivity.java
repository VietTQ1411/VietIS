package com.example.vietis.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietis.R;
import com.example.vietis.inteface.IView;
import com.example.vietis.notifications.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "MAIN_ACTIVITY";
    private Button btnFacebook;
    private Button btnGoogle;
    private Button btnLogin;
    private TextView signUp;

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
                String msg = getString(R.string.msg_token_fmt,token);
                Log.d(TAG,msg);
                Toast.makeText(MainActivity.this, msg,Toast.LENGTH_LONG).show();
            }
        });
        createNotificationChannels();
        setContentView(R.layout.activity_main);

        mappingUI();
        setupUI();
    }

    @Override
    public void mappingUI() {
        btnFacebook = findViewById(R.id.main_facebook);
        btnGoogle = findViewById(R.id.main_google);
        btnLogin = findViewById(R.id.main_login);
        signUp = findViewById(R.id.main_sign_up);
    }

    @Override
    public void setupUI() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}